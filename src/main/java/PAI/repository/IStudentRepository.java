package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Student;

import java.util.Optional;

public interface IStudentRepository extends IRepository <StudentID, Student> {

    Optional<Student> getStudentByID(StudentID studentID);

    Optional<StudentID> findIdByStudent (Student student);

    Student save(Student student);

    Iterable<Student> findAll();

    Optional<Student> ofIdentity(StudentID studentID);

    boolean containsOfIdentity(StudentID studentID);

    boolean containsByStudentIDOrNIF(StudentID studentID, NIF nif);
}
