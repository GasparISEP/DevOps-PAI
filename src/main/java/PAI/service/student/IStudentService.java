package PAI.service.student;

import PAI.VOs.*;
import PAI.domain.student.Student;

import java.util.List;

public interface IStudentService {

    Student registerStudent( Name name, NIF nif, PhoneNumber phoneNumber, Email email, Street street,
                            PostalCode postalCode, Location location, Country country)
            throws IllegalArgumentException;

    int getLastStudentID();

    List<Student> getAllStudents();
}