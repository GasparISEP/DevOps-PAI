package PAI.factory;

import PAI.VOs.*;
import PAI.domain.Address;
import PAI.domain.Student;

public interface IStudentFactory {

    Student newStudent(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Address address, StudentAcademicEmail academicEmail);

}
