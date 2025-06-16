package PAI.service.student;

import PAI.VOs.*;
import PAI.domain.student.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    Student registerStudent( Name name, NIF nif, PhoneNumber phoneNumber, Email email, Street street,
                            PostalCode postalCode, Location location, Country country)
            throws IllegalArgumentException;

    int getLastStudentID();

    List<Student> getAllStudents();

    Name getNameByStudentID(StudentID studentID);

    Optional<Student> findByID(StudentID studentID);

}