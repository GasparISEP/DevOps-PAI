package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Student;

import java.util.Optional;

public interface IStudentRepository extends IRepository <StudentID, Student> {

    boolean registerStudent(StudentID studentID, Name name, NIF NIF, PhoneNumber phone,
                            Email email, Street street, PostalCode postalCode, Location location, Country country,
                            StudentAcademicEmail academicEmail) throws Exception;

    Optional<Student> getStudentByID(StudentID studentID);

    Optional<StudentID> findIdByStudent (Student student);

    Student save(Student student);

    Iterable<Student> findAll();

    Optional<Student> ofIdentity(StudentID studentID);

    boolean containsOfIdentity(StudentID studentID);
}
