package PAI.persistence.springdata;

import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepositorySpringData extends JpaRepository<TeacherDataModel, TeacherIDDataModel> {

    boolean existsByIDorNIF (TeacherIDDataModel teacherIDDataModel, NIFDataModel nifDataModel);
}
