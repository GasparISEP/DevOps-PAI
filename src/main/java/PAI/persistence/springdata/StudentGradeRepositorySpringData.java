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
    public boolean addGradeToStudent(Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception {
        StudentGrade sg1 = iStudentGradeFactory.newGradeStudent(grade,date,student,courseEditionID);
        StudentGradeDM studentGradeDM = studentGradeMapper.toData(sg1);
        if (iStudentGradeRepositorySpringData.existsById(studentGradeDM.getId())) return false;
        iStudentGradeRepositorySpringData.save(studentGradeDM);
        return true;
    }
}
