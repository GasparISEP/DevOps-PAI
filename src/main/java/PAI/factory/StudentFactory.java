package PAI.factory;

import PAI.domain.Address;
import PAI.domain.Student;

public interface StudentFactory {

    Student newStudent(String uniqueNumber, String name, String NIF, String phone, String email, Address address);

}
