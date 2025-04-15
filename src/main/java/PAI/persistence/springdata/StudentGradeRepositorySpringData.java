package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeRepository;
import PAI.mapper.StudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;

import java.util.Optional;

public class StudentGradeRepositorySpringData  {

    IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData;
    IStudentGradeFactory iStudentGradeFactory;
    StudentGradeMapper studentGradeMapper;

    public StudentGradeRepositorySpringData (IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData, IStudentGradeFactory iStudentGradeFactory, StudentGradeMapper studentGradeMapper){
        this.iStudentGradeRepositorySpringData = iStudentGradeRepositorySpringData;
        this.iStudentGradeFactory = iStudentGradeFactory;
        this.studentGradeMapper = studentGradeMapper;
    }

}
