package PAI.factory;

import PAI.VOs.StudentID;
import PAI.domain.Address;
import PAI.domain.Student;

public class StudentFactoryImpl implements IStudentFactory {

    public Student newStudent(StudentID studentID, String name, String NIF, String phone, String email, Address address) {
        return new Student(studentID, name, NIF, phone, email, address);
    }
}
