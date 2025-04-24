package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.mapper.*;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.StudentDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.repository.IStudentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepositorySpringData implements IStudentRepository {

    private IStudentRepositorySpringData studentRepositorySpringData;
    private IStudentMapper studentMapper;
    private IStudentIDMapper studentIDMapper;
    private INIFMapper nifMapper;

    public StudentRepositorySpringData  (IStudentRepositorySpringData studentRepositorySpringData, IStudentMapper studentMapper, IStudentIDMapper studentIDMapper, INIFMapper nifMapper ) {

        if (studentRepositorySpringData == null)
            throw new IllegalArgumentException("Student Repository SpringData must not be null");
        if (studentMapper == null)
            throw new IllegalArgumentException("Student Mapper must not be null");
        if (studentIDMapper == null)
            throw new IllegalArgumentException("StudentID Mapper must not be null");
        if (nifMapper == null)
            throw new IllegalArgumentException("NIF Mapper must not be null");

        this.studentRepositorySpringData = studentRepositorySpringData;
        this.studentMapper = studentMapper;
        this.studentIDMapper = studentIDMapper;
        this.nifMapper = nifMapper;

    }

    @Override
    public Student save(Student student) {
        StudentDataModel studentDataModel = studentMapper.domainToDataModel(student);
        StudentDataModel savedStudentDataModel = studentRepositorySpringData.save(studentDataModel);
        try {
            return studentMapper.dataModelToDomain(savedStudentDataModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        return studentRepositorySpringData.findAll().stream()
                .map(dataModel -> {
                    try {
                        return studentMapper.dataModelToDomain(dataModel);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to convert StudentDataModel to Student", e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Student> ofIdentity(StudentID studentID) {
        try {
            StudentIDDataModel dataModel = studentIDMapper.domainToDataModel(studentID);
            Optional<StudentDataModel> dataModelOptional = studentRepositorySpringData.findById(dataModel);
            if (dataModelOptional.isPresent()) {
                return Optional.of(studentMapper.dataModelToDomain(dataModelOptional.get()));
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve and map Student by ID", e);
        }
    }

    @Override
    public boolean containsOfIdentity(StudentID studentID) {
        try {
            StudentIDDataModel dataModel = studentIDMapper.domainToDataModel(studentID);
            return studentRepositorySpringData.existsById(dataModel);
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean existsByStudentIDOrNIF(StudentID studentID, NIF nif) {
        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(studentID);
        NIFDataModel nifDataModel = nifMapper.domainToDataModel(nif);

        return studentRepositorySpringData.existsByStudentIDOrNIF(studentIDDataModel, nifDataModel);
        }
}



