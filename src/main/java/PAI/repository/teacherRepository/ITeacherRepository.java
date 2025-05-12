package PAI.repository.teacherRepository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Teacher;

public interface ITeacherRepository extends IRepository<TeacherID, Teacher> {

    boolean existsByTeacherIdOrNif(TeacherID teacherID, NIF nif);

    boolean containsOfIdentity(TeacherID teacherID);
}
