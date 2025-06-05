package PAI.domain.repositoryInterfaces.studentGrade;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.studentGrade.StudentGrade;

import java.util.Optional;

public interface IStudentGradeRepository extends IRepository<StudentGradeID, StudentGrade> {

    StudentGrade save(StudentGrade entity);

    Iterable<StudentGrade> findAll();

    Optional<StudentGrade> ofIdentity(StudentGradeID studentGradeID);

    boolean containsOfIdentity(StudentGradeID studentGradeID);
}

