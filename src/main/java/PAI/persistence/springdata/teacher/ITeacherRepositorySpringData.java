package PAI.persistence.springdata.teacher;

import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITeacherRepositorySpringData extends JpaRepository<TeacherDataModel, TeacherIDDataModel> {

    boolean existsByTeacherIdOrNif (TeacherIDDataModel teacherId, NIFDataModel nif);

    Optional<TeacherDataModel> findByTeacherId (TeacherIDDataModel teacherID);

    Iterable<TeacherDataModel> findByDepartmentID (DepartmentIDDataModel departmentID);
}
