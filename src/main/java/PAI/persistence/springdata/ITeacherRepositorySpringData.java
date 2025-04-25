package PAI.persistence.springdata;

import PAI.VOs.NIF;
import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITeacherRepositorySpringData extends JpaRepository<TeacherDataModel, String> {

    Optional<TeacherDataModel> findTeacherByTeacherAcronymOrNIF(TeacherAcronym teacherAcronym, NIF nif);

    boolean existsByIDorNIF (TeacherIDDataModel teacherIDDataModel, NIFDataModel nifDataModel);
}
