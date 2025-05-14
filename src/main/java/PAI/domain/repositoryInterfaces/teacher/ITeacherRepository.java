package PAI.domain.repositoryInterfaces.teacher;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.teacher.Teacher;

public interface ITeacherRepository extends IRepository<TeacherID, Teacher> {

    boolean existsByTeacherIdOrNif(TeacherID teacherID, NIF nif);

    boolean containsOfIdentity(TeacherID teacherID);
}
