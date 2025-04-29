package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Department;
import PAI.domain.Teacher;

import java.util.Optional;

public interface ITeacherRepository extends IRepository<TeacherID, Teacher> {

    boolean existsByIDorNIF(TeacherID teacherID, NIF nif);
}
