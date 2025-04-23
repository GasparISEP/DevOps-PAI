package PAI.persistence.springdata;

import PAI.VOs.NIF;
import PAI.VOs.TeacherAcronym;
import PAI.persistence.datamodel.TeacherDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITeacherRepositorySpringData extends JpaRepository<TeacherDataModel, String> {

    Optional<TeacherDataModel> findTeacherByTeacherAcronymOrNIF(TeacherAcronym teacherAcronym, NIF nif);
}
