package PAI.service;

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
}
