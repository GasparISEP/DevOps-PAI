package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeRepository;
import PAI.mapper.StudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class StudentGradeRepositorySpringData  {
    private static final Logger log = LoggerFactory.getLogger(StudentGradeRepositorySpringData.class);


    IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData;
    StudentGradeMapper studentGradeMapper;

    public StudentGradeRepositorySpringData(IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData, StudentGradeMapper studentGradeMapper) {
        this.iStudentGradeRepositorySpringData = iStudentGradeRepositorySpringData;
        this.studentGradeMapper = studentGradeMapper;
    }

    public StudentGrade save(StudentGrade entity)  {
        try{
            StudentGradeDM studentGradeDM = studentGradeMapper.toData(entity);
            iStudentGradeRepositorySpringData.save(studentGradeDM);
        } catch (Exception e) {
            return entity;
        }
        return entity;
    }

}
