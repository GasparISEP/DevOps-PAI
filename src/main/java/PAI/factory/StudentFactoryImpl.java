package PAI.factory;

import PAI.domain.Address;
import PAI.domain.Student;

public class StudentFactoryImpl implements IStudentFactory {

    public Student newStudent(String uniqueNumber, String name, String NIF, String phone, String email, Address address) {
        return new Student(uniqueNumber, name, NIF, phone, email, address);
    }
}
