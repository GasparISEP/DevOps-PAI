package PAI.service.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.domain.student.IStudentFactory;
import PAI.domain.repositoryInterfaces.student.IStudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {

    private IStudentFactory _studentFactory;
    private IStudentRepository _studentRepository;

    public StudentServiceImpl(IStudentFactory studentFactory, IStudentRepository studentRepository) {

        if(studentFactory == null)
            throw new IllegalArgumentException("Student Factory cannot be null!");

        _studentFactory = studentFactory;

        if(studentRepository == null)
            throw new IllegalArgumentException("Student Repository cannot be null!");

        _studentRepository = studentRepository;
    }

    public Student registerStudent (StudentID studentID, Name name, NIF nif, PhoneNumber phoneNumber, Email email, Street street,
                                    PostalCode postalCode, Location location, Country country, StudentAcademicEmail academicEmail) throws IllegalArgumentException {

        Student student = _studentFactory.newStudent(studentID, name, nif, phoneNumber, email, street, postalCode, location, country, academicEmail);

        if(_studentRepository.existsByStudentIDOrNIF(studentID, nif))
            throw new IllegalArgumentException ("Student with this information is already registered!");

        return _studentRepository.save(student);
    }

    public int getLastStudentID(){
        return _studentRepository.lastStudentID();
    }
}
