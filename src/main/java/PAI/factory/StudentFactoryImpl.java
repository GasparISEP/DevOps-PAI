package PAI.factory;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.repository.IStudentRepository;
import org.springframework.stereotype.Component;

@Component
public class StudentFactoryImpl implements IStudentFactory {

    private IStudentRepository _studentRepository;

    public StudentFactoryImpl(IStudentRepository studentRepository) {

        if(studentRepository == null)
            throw new IllegalArgumentException("Student Repository cannot be null!");

        _studentRepository = studentRepository;
    }

    public Student newStudent(StudentID studentID, Name name, NIF nif, PhoneNumber phone, Email email,
                              Street street, PostalCode postalCode, Location location, Country country,
                              StudentAcademicEmail academicEmail) throws Exception {

        Address address = createAddress(street, postalCode, location, country);

        Student student = new Student(studentID, name, nif, phone, email, address, academicEmail);

        if(_studentRepository.existsByStudentIDOrNIF(studentID, nif))
            throw new Exception ("Student with this information is already registered!");

        return student;
    }

    public Address createAddress (Street street, PostalCode postalCode, Location location, Country country) {

        return new Address(street, postalCode, location, country);
    }

    public Student newStudentFromDataModel(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Address address, StudentAcademicEmail academicEmail) {

        return new Student(studentID, name, NIF, phone, email, address, academicEmail);
    }
}
