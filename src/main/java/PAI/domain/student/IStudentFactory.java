package PAI.domain.student;

import PAI.VOs.*;

public interface IStudentFactory {

    Student newStudent(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Street street, PostalCode postalCode, Location location, Country country, StudentAcademicEmail academicEmail) throws IllegalArgumentException;

    Student newStudentFromDataModel(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Address address, StudentAcademicEmail academicEmail);

}
