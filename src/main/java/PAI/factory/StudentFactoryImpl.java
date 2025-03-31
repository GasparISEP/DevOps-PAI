package PAI.factory;

import PAI.VOs.*;
import PAI.domain.Address;
import PAI.domain.Student;

public class StudentFactoryImpl implements IStudentFactory {

    public Student newStudent(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Address address, StudentAcademicEmail academicEmail) {
        return new Student(studentID, name, NIF, phone, email, address, academicEmail);
    }
}
