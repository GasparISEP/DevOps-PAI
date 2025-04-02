package PAI.factory;

import PAI.VOs.*;
import PAI.domain.Address;
import PAI.domain.Student;

public class StudentFactoryImpl implements IStudentFactory {

    public Student newStudent(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email,
                              Street street, PostalCode postalCode, Location location, Country country,
                              StudentAcademicEmail academicEmail) {

        AddressVO address = createAddress(street, postalCode, location, country);

        return new Student(studentID, name, NIF, phone, email, address, academicEmail);
    }

    public AddressVO createAddress (Street street, PostalCode postalCode, Location location, Country country) {
        return new AddressVO(street, postalCode, location, country);
    }
}
