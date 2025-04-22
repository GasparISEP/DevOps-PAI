package PAI.persistence.springdata;


import PAI.domain.StudentGrade;
import PAI.mapper.IStudentGradeIDMapper;
import PAI.mapper.IStudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;

public class StudentGradeRepositorySpringData  {

    private IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData;
    private IStudentGradeMapper studentGradeMapper;
    private IStudentGradeIDMapper studentGradeIDMapper;


    public StudentGradeRepositorySpringData(IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData, IStudentGradeMapper studentGradeMapper, IStudentGradeIDMapper iStudentGradeIDMapper) {
        this.iStudentGradeRepositorySpringData = iStudentGradeRepositorySpringData;
        this.studentGradeMapper = studentGradeMapper;
        this.studentGradeIDMapper = iStudentGradeIDMapper;
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
