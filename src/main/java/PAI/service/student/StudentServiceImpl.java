package PAI.service.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.domain.student.IStudentFactory;
import PAI.domain.repositoryInterfaces.student.IStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements IStudentService {

    private final IStudentFactory _studentFactory;
    private final IStudentRepository _studentRepository;

    public StudentServiceImpl(IStudentFactory studentFactory, IStudentRepository studentRepository) {

        if (studentFactory == null)
            throw new IllegalArgumentException("Student Factory cannot be null!");

        if (studentRepository == null)
            throw new IllegalArgumentException("Student Repository cannot be null!");

        this._studentFactory = studentFactory;
        this._studentRepository = studentRepository;
    }

    @Override
    public Student registerStudent(Name name, NIF nif, PhoneNumber phoneNumber, Email email,
                                   Street street, PostalCode postalCode, Location location,
                                   Country country) {

        StudentID lastStudentIDPlusOne = new StudentID(getLastStudentID()+1);
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(lastStudentIDPlusOne.getUniqueNumber());

        Student student = _studentFactory.newStudent(lastStudentIDPlusOne, name, nif, phoneNumber, email,
                street, postalCode, location, country, academicEmail);

        _studentRepository.existsByStudentIDOrNIF(lastStudentIDPlusOne, nif);

        return _studentRepository.save(student);

    }

    @Override
    public int getLastStudentID() {
        return _studentRepository.lastStudentID();
    }

    @Override
    public List<Student> getAllStudents() {
        Iterable<Student> iterable = _studentRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList(); // ou .collect(...)
    }

    public Name getNameByStudentID(StudentID studentID) {
        return _studentRepository.ofIdentity(studentID)
                .map(Student::getStudentName)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentID));
    }
}