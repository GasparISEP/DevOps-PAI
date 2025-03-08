package PAI.factory;

import PAI.domain.Address;
import PAI.domain.Student;

public interface StudentFactoryInterface {

    Student newStudent(int uniqueNumber, String name, String NIF, String phone, String email, Address address);

}
