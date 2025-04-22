package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.mapper.StudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class StudentGradeRepositorySpringData {
    private static final Logger log = LoggerFactory.getLogger(StudentGradeRepositorySpringData.class);


    IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData;
    StudentGradeMapper studentGradeMapper;

    public StudentGradeRepositorySpringData(IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData, StudentGradeMapper studentGradeMapper) {
        this.iStudentGradeRepositorySpringData = iStudentGradeRepositorySpringData;
        this.studentGradeMapper = studentGradeMapper;
    }

    public boolean save(StudentGrade studentGrade) throws Exception {
        try {
            StudentGradeDM studentGradeDM = studentGradeMapper.toData(studentGrade);
            iStudentGradeRepositorySpringData.save(studentGradeDM);
            return true;
        }
        catch (Exception e){
            log.error("Failed to save", e);
        return false;
    }
}
}
