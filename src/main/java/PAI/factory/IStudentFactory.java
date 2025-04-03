package PAI.factory;

import PAI.VOs.*;
import PAI.domain.Student;

public interface IStudentFactory {

    Student newStudent(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Street street, PostalCode postalCode, Location location, Country country, StudentAcademicEmail academicEmail);

    public Address createAddress (Street street, PostalCode postalCode, Location location, Country country);
}
