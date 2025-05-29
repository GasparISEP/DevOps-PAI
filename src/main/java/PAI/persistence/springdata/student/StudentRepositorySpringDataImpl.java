package PAI.persistence.springdata.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.mapper.*;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.student.IStudentMapper;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.student.StudentDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.domain.repositoryInterfaces.student.IStudentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepositorySpringDataImpl implements IStudentRepository {

    private IStudentRepositorySpringData studentRepositorySpringData;
    private IStudentMapper studentMapper;
    private IStudentIDMapper studentIDMapper;
    private INIFMapper nifMapper;

    public StudentRepositorySpringDataImpl(IStudentRepositorySpringData studentRepositorySpringData, IStudentMapper studentMapper, IStudentIDMapper studentIDMapper, INIFMapper nifMapper ) {

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
        String nifNumber = nif.getNIF();
        String nifCountry = nif.getCountry().toString();

        boolean studentIDExists = studentRepositorySpringData.existsByStudentID(studentIDDataModel);
        boolean nifExists = studentRepositorySpringData.existsByNIF_NifNumberAndNIF_NifCountry(nifNumber, nifCountry);

        if (studentIDExists) {
            throw new IllegalArgumentException("StudentID already exists!");
        }

        if (nifExists) {
            throw new IllegalArgumentException("NIF already exists!");
        }

        return false;
    }


    @Override
    public int lastStudentID() {
        Integer last = studentRepositorySpringData.lastStudentID();
        if(last == 0 ) return 1000000;
        return last;
    }



}



