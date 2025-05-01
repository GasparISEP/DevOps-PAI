package PAI.persistence.springdata;

import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITeacherRepositorySpringData extends JpaRepository<TeacherDataModel, TeacherIDDataModel> {

    boolean existsByTeacherIdOrNif (TeacherIDDataModel teacherId, NIFDataModel nif);

    Optional<TeacherDataModel> findByTeacherId (TeacherIDDataModel teacherID);
}
