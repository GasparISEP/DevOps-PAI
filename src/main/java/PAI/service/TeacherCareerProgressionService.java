package PAI.service;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.repository.ITeacherCareerProgressionRepository;

import java.util.Objects;

public class TeacherCareerProgressionService implements ITeacherCareerProgressionService {

    private ITeacherCareerProgressionRepository _TCPrepository;
    private ITeacherCareerProgressionFactory _TCPfactory;

    public TeacherCareerProgressionService(ITeacherCareerProgressionRepository teacherCareerProgressionRepo, ITeacherCareerProgressionFactory teacherCareerProgressionFactory){
        Objects.requireNonNull(_TCPrepository = teacherCareerProgressionRepo, "Teacher Career Progression Repository cannot be null!");
        Objects.requireNonNull(_TCPfactory = teacherCareerProgressionFactory, "Teacher Career Progression Factory cannot be null!");
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
}
