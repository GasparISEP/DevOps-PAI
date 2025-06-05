package PAI.service.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.domain.student.IStudentFactory;
import PAI.domain.repositoryInterfaces.student.IStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Student registerStudent(StudentID studentID, Name name, NIF nif, PhoneNumber phoneNumber, Email email,
                                   Street street, PostalCode postalCode, Location location,
                                   Country country, StudentAcademicEmail academicEmail) {

        Student student = _studentFactory.newStudent(studentID, name, nif, phoneNumber, email,
                street, postalCode, location, country, academicEmail);

        _studentRepository.existsByStudentIDOrNIF(studentID, nif);

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
}