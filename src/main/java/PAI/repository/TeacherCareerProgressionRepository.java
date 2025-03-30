package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.factory.ITeacherCareerProgressionListFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherCareerProgressionRepository implements IRepository<TeacherCareerProgressionID, TeacherCareerProgression > {

    private ITeacherCareerProgressionFactory _ITeacherCareerProgressionFactory;
    private List<TeacherCareerProgression> _teacherCareerProgressions;

    public TeacherCareerProgressionRepository (ITeacherCareerProgressionFactory tcpFactory, ITeacherCareerProgressionListFactory tcpListFactory){

        if (tcpFactory == null || tcpListFactory == null){
            throw new IllegalStateException("Factory cannot be null!");
        }

        this._ITeacherCareerProgressionFactory = tcpFactory;
        this._teacherCareerProgressions = new ArrayList<>();
    }

    public boolean createTeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception {

        if (date == null || teacherCategoryID == null || wp == null || teacherID == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        TeacherCareerProgression tcp = _ITeacherCareerProgressionFactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID);

        save(tcp);

        return true;
    }

    @Override
    public TeacherCareerProgression save(TeacherCareerProgression tcp) {

        _teacherCareerProgressions.add(tcp);

        return tcp;
    }

    public Iterable<TeacherCareerProgression> findAll() {
        if (_teacherCareerProgressions.isEmpty()){
            throw new IllegalStateException("Teacher Career Progression List is empty.");
        }
        return _teacherCareerProgressions;
    }

    @Override
    public Optional<TeacherCareerProgression> ofIdentity(TeacherCareerProgressionID id) {
        return _teacherCareerProgressions.stream()
                .filter(tcp -> tcp.identity().equals(id))
                .findAny();
    }

    @Override
    public boolean containsOfIdentity(TeacherCareerProgressionID id) {
        if (!ofIdentity(id).isPresent()){
            return false;
        }
        return true;
    }

    public Optional<TeacherCareerProgression> findLastTCPFromTeacherID(TeacherID teacherID) {
        TeacherCareerProgression latestTCP = null;

        for (TeacherCareerProgression tcp : _teacherCareerProgressions) {
            if (tcp.getTeacherID().equals(teacherID)) {
                if (latestTCP == null || tcp.getDate().getLocalDate().isAfter(latestTCP.getDate().getLocalDate())) {
                    latestTCP = tcp;
                }
            }
        }
        return Optional.ofNullable(latestTCP);
    }
}
