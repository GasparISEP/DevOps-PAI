package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Teacher;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;

import java.util.Optional;

public interface ITeacherRepository extends IRepository<TeacherID, Teacher> {

    boolean existsByTeacherIdOrNif(TeacherID teacherID, NIF nif);

    boolean containsOfIdentity(TeacherID teacherID);
}
