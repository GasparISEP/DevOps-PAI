package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Student;

import java.util.Optional;

public interface IStudentRepository extends IRepository <StudentID, Student> {

    Student save(Student student);

    Iterable<Student> findAll();

    Optional<Student> ofIdentity(StudentID studentID);

    boolean containsOfIdentity(StudentID studentID);

    boolean existsByStudentIDOrNIF(StudentID studentID, NIF nif);
}
