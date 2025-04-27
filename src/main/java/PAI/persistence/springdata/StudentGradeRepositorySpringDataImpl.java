package PAI.persistence.springdata;
import PAI.VOs.StudentGradeID;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeRepository;
import PAI.mapper.IStudentGradeIDMapper;
import PAI.mapper.IStudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentGradeRepositorySpringDataImpl implements IStudentGradeRepository {

    private IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData;
    private IStudentGradeMapper studentGradeMapper;
    private IStudentGradeIDMapper studentGradeIDMapper;


    public StudentGradeRepositorySpringDataImpl(IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData, IStudentGradeMapper studentGradeMapper, IStudentGradeIDMapper iStudentGradeIDMapper) {
        this.iStudentGradeRepositorySpringData = iStudentGradeRepositorySpringData;
        this.studentGradeMapper = studentGradeMapper;
        this.studentGradeIDMapper = iStudentGradeIDMapper;
    }

    @Override
    public StudentGrade save(StudentGrade entity) {
        try {
            StudentGradeDM studentGradeDM = studentGradeMapper.toData(entity);
            iStudentGradeRepositorySpringData.save(studentGradeDM);
        } catch (Exception e) {
            return entity;
        }
        return entity;
    }

    @Override
    public Iterable<StudentGrade> findAll() {
        List<StudentGrade> allStudentGrades = new ArrayList<>();
        List<StudentGradeDM> allStudentGradesDataModel = iStudentGradeRepositorySpringData.findAll();
        for (StudentGradeDM existingStudentGrade : allStudentGradesDataModel) {
            try {
                StudentGrade studentGrade = studentGradeMapper.toDomain(existingStudentGrade);
                allStudentGrades.add(studentGrade);
            } catch (Exception e) {
                return Collections.emptyList();
            }
        }
        return allStudentGrades;
    }

    @Override
    public Optional<StudentGrade> ofIdentity(StudentGradeID studentGradeID) {
        try {
            StudentGradeIDDataModel studentGradeDMId = studentGradeIDMapper.toDataModel(studentGradeID);
            Optional<StudentGradeDM> studentGradeDMOpt = iStudentGradeRepositorySpringData.findById(studentGradeDMId);

            if (studentGradeDMOpt.isPresent()) {
                StudentGrade studentGrade = studentGradeMapper.toDomain(studentGradeDMOpt.get());
                return Optional.of(studentGrade);
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(StudentGradeID studentGradeID) {
        try {
            StudentGradeIDDataModel studentGradeDMId = studentGradeIDMapper.toDataModel(studentGradeID);
            Optional<StudentGradeDM> studentGradeDMOpt = iStudentGradeRepositorySpringData.findById(studentGradeDMId);

            if (studentGradeDMOpt.isPresent()) {
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }
}