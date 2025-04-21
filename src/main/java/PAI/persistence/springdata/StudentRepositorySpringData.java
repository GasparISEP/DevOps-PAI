package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.mapper.NIFMapper;
import PAI.mapper.StudentIDMapper;
import PAI.mapper.StudentMapper;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.StudentDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.repository.IStudentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepositorySpringData implements IStudentRepository {

    private IStudentRepositorySpringData studentRepositorySpringData;
    private StudentMapper studentMapper;
    private StudentIDMapper studentIDMapper;
    private NIFMapper nifMapper;

    public StudentRepositorySpringData  (IStudentRepositorySpringData studentRepositorySpringData, StudentMapper studentMapper, StudentIDMapper studentIDMapper, NIFMapper nifMapper ) {

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
    public Optional<Student> getStudentByID(StudentID studentID) {
        int id = studentID.getUniqueNumber();
        return studentRepositorySpringData.findById(id)
                .map(dataModel -> {
                    try {
                        return studentMapper.dataModelToDomain(dataModel);
                    } catch (Exception e) {
                        throw new RuntimeException("Mapping error", e);
                    }
                });
    }

    @Override
    public Optional<StudentID> findIdByStudent(Student student) {

            StudentDataModel dataModel = studentMapper.domainToDataModel(student);
            StudentIDDataModel idDataModel = dataModel.getStudentID();

            return Optional.of(studentIDMapper.dataModelToDomain(idDataModel));
    }

    @Override
    public Student save(Student student) {
        StudentDataModel studentDataModel = studentMapper.domainToDataModel(student);
        studentRepositorySpringData.save(studentDataModel);
        return student;
    }

    @Override
    public Iterable<Student> findAll() {
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
        return getStudentByID(studentID);
    }

    @Override
    public boolean containsOfIdentity(StudentID studentID) {
        return studentRepositorySpringData.existsById( studentID.getUniqueNumber());
    }

    @Override
    public boolean existsByStudentIDOrNIF(StudentID studentID, NIF nif) {
        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(studentID);
        NIFDataModel nifDataModel = nifMapper.domainToDataModel(nif);

        return studentRepositorySpringData.existsByStudentIDOrNIF(studentIDDataModel, nifDataModel);
        }

}



