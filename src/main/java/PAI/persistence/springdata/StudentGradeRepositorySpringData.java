package PAI.persistence.springdata;
import PAI.domain.StudentGrade;
import PAI.mapper.IStudentGradeIDMapper;
import PAI.mapper.IStudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class StudentGradeRepositorySpringData  {

    private IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData;
    private IStudentGradeMapper studentGradeMapper;
    private IStudentGradeIDMapper studentGradeIDMapper;


    public StudentGradeRepositorySpringData(IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData, IStudentGradeMapper studentGradeMapper, IStudentGradeIDMapper iStudentGradeIDMapper) {
        this.iStudentGradeRepositorySpringData = iStudentGradeRepositorySpringData;
        this.studentGradeMapper = studentGradeMapper;
        this.studentGradeIDMapper = iStudentGradeIDMapper;
    }

    public StudentGrade save(StudentGrade entity) {
        try{
            StudentGradeDM studentGradeDM = studentGradeMapper.toData(entity);
            iStudentGradeRepositorySpringData.save(studentGradeDM);
        } catch (Exception e) {
            return entity;
        }
        return entity;
    }

public Iterable<StudentGrade> findAll(){
    List<StudentGrade> allStudentGrades = new ArrayList<>();
    List<StudentGradeDM> allStudentGradesDataModel = iStudentGradeRepositorySpringData.findAll();
    for(StudentGradeDM existingStudentGrade : allStudentGradesDataModel){
        try {
            StudentGrade studentGrade = studentGradeMapper.toDomain(existingStudentGrade);
            allStudentGrades.add(studentGrade);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
    return allStudentGrades;
}

    public Optional<StudentGrade> ofIdentity(Long id) {
        Optional<StudentGradeDM> studentGradeDMOpt = iStudentGradeRepositorySpringData.findById(id);

        if (studentGradeDMOpt.isEmpty()) {
            return Optional.empty();
        }
        try {
            StudentGrade studentGrade = studentGradeMapper.toDomain(studentGradeDMOpt.get());
            return Optional.of(studentGrade);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
