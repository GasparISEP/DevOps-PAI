package PAI.domain.student;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class StudentFactoryImpl implements IStudentFactory {

    public Student newStudent(StudentID studentID, Name name, NIF nif, PhoneNumber phone, Email email,
                              Street street, PostalCode postalCode, Location location, Country country,
                              StudentAcademicEmail academicEmail) {

        Address address = createAddress(street, postalCode, location, country);

        Student student = new Student(studentID, name, nif, phone, email, address, academicEmail);

        return student;
    }

    private Address createAddress (Street street, PostalCode postalCode, Location location, Country country) {

        return new Address(street, postalCode, location, country);
    }

    public Student newStudentFromDataModel(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Address address, StudentAcademicEmail academicEmail) {

        return new Student(studentID, name, NIF, phone, email, address, academicEmail);
    }
}
