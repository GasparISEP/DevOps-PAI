package PAI.service.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.repository.ITeacherCareerProgressionRepository;

import java.util.Objects;
import java.util.Optional;

public class TeacherCareerProgressionServiceImpl implements ITeacherCareerProgressionService {

    private ITeacherCareerProgressionRepository _TCPrepository;
    private ITeacherCareerProgressionFactory _TCPfactory;

    public TeacherCareerProgressionServiceImpl(ITeacherCareerProgressionRepository teacherCareerProgressionRepo, ITeacherCareerProgressionFactory teacherCareerProgressionFactory) {
        this._TCPrepository = Objects.requireNonNull(teacherCareerProgressionRepo, "Teacher Career Progression Repository cannot be null!");
        this._TCPfactory = Objects.requireNonNull(teacherCareerProgressionFactory, "Teacher Career Progression Factory cannot be null!");
    }

    public boolean createTeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception {

        if (date == null || teacherCategoryID == null || wp == null || teacherID == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        TeacherCareerProgression tcp = _TCPfactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID);

        if (_TCPrepository.containsOfIdentity(tcp.getID())) {
            return false;
        }

        _TCPrepository.save(tcp);

        return true;
    }

    public boolean updateTeacherCategoryInTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, TeacherID teacherID) throws Exception {

        Optional<TeacherCareerProgression> optionalTCP = _TCPrepository.findLastTCPFromTeacherID(teacherID);

        if (optionalTCP.isEmpty())
            return false;

        TeacherCareerProgression lastTCP = optionalTCP.get();

        if (!lastTCP.isLastDateEqualOrBeforeNewDate(date))
            return false;

        WorkingPercentage workingPercentage = lastTCP.getWorkingPercentage();

        if (lastTCP.getTeacherCategoryID().equals(teacherCategoryID))
            return false;

        createTeacherCareerProgression(date, teacherCategoryID, workingPercentage, teacherID);
        return true;
    }

    public boolean updateWorkingPercentageInTeacherCareerProgression(Date date, WorkingPercentage workingPercentage, TeacherID teacherID) throws Exception {

        if(date == null || workingPercentage == null || teacherID == null)
            return false;

        Optional<TeacherCareerProgression> optionalTCP = _TCPrepository.findLastTCPFromTeacherID(teacherID);

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
}
