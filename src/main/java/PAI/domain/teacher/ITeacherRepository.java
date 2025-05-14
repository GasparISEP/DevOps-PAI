package PAI.domain.teacher;

import PAI.VOs.*;
import PAI.ddd.IRepository;

public interface ITeacherRepository extends IRepository<TeacherID, Teacher> {

    boolean existsByTeacherIdOrNif(TeacherID teacherID, NIF nif);

    boolean containsOfIdentity(TeacherID teacherID);
}
