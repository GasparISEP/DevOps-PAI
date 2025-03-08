package PAI.factory;

import PAI.domain.Address;
import PAI.domain.Student;

public class StudentFactory implements StudentFactoryInterface {

    public Student newStudent(int uniqueNumber, String name, String NIF, String phone, String email, Address address) {
        return new Student(uniqueNumber, name, NIF, phone, email, address);
    }
}
