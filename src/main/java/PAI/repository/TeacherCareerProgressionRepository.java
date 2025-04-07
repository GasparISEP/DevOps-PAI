package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.factory.ITeacherCareerProgressionListFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherCareerProgressionRepository implements ITeacherCareerProgressionRepository {

    private ITeacherCareerProgressionFactory _teacherCareerProgressionFactory;
    private List<TeacherCareerProgression> _teacherCareerProgressions;

    public TeacherCareerProgressionRepository (ITeacherCareerProgressionFactory tcpFactory, ITeacherCareerProgressionListFactory tcpListFactory){

        if (tcpFactory == null || tcpListFactory == null){
            throw new IllegalStateException("Factory cannot be null!");
        }

        this._teacherCareerProgressionFactory = tcpFactory;
        this._teacherCareerProgressions = tcpListFactory.createTeacherCareerProgressionList();
    }

    public boolean createTeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception {

        if (date == null || teacherCategoryID == null || wp == null || teacherID == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        TeacherCareerProgression tcp = _teacherCareerProgressionFactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID);

        if (isTeacherCareerProgressionDuplicate(tcp)) {
            throw new Exception("Teacher Career Progression already exists.");
        }

        save(tcp);

        return true;
    }

    private boolean isTeacherCareerProgressionDuplicate(TeacherCareerProgression tcp) {
        for (TeacherCareerProgression existingTCP : _teacherCareerProgressions){
            if(existingTCP.sameAs(tcp)){
                return true;
            }
        }
        return false;
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
        return new ArrayList<>(_teacherCareerProgressions);
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
                if (latestTCP == null || tcp.isDateAfter(latestTCP)) {
                    latestTCP = tcp;
                }
            }
        }
        return Optional.ofNullable(latestTCP);
    }

    public boolean updateWorkingPercentageInTeacherCareerProgression(Date date, WorkingPercentage workingPercentage, TeacherID teacherID) throws Exception {

        if(date == null || workingPercentage == null || teacherID == null)
            return false;

        Optional<TeacherCareerProgression> optionalTCP = findLastTCPFromTeacherID(teacherID);

        if (optionalTCP.isEmpty())
            return false;

        TeacherCareerProgression lastTCP = optionalTCP.get();

        if (!lastTCP.isLastDateEqualOrBeforeNewDate(date))
            return false;

        TeacherCategoryID teacherCategoryID = lastTCP.getTeacherCategoryID();

        if(lastTCP.getWorkingPercentage().equals(workingPercentage))
            return false;

        createTeacherCareerProgression(date, teacherCategoryID, workingPercentage, teacherID);

        return true;
    }

    public boolean updateTeacherCategoryInTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, TeacherID teacherID) throws Exception {

        if(date == null || teacherCategoryID == null || teacherID == null)
            return false;

        Optional<TeacherCareerProgression> optionalTCP = findLastTCPFromTeacherID(teacherID);

        if (optionalTCP.isEmpty())
            return false;

        TeacherCareerProgression lastTCP = optionalTCP.get();

        if(lastTCP.isLastDateEqualOrBeforeNewDate(date))
            return false;

        WorkingPercentage workingPercentage = lastTCP.getWorkingPercentage();

        if(lastTCP.getTeacherCategoryID().equals(teacherCategoryID))
            return false;

        createTeacherCareerProgression(date, teacherCategoryID, workingPercentage, teacherID);

        return true;
    }
}
