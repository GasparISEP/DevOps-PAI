package PAI.service;

import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeRepository;

public class StudentGradeService {

    private final IStudentGradeFactory studentGradeFactory;
    private final IStudentGradeRepository studentGradeRepository;

    public StudentGradeService(IStudentGradeFactory studentGradeFactory, IStudentGradeRepository studentGradeRepository) {
        this.studentGradeFactory = studentGradeFactory;
        this.studentGradeRepository = studentGradeRepository;
    }
}
