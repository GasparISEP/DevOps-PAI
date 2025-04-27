package PAI.persistence.springdata.teacherCareerProgression;

import PAI.persistence.datamodel.TeacherCareerProgressionDataModel;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ITeacherCareerProgressionRepoSpringData extends JpaRepository <TeacherCareerProgressionDataModel, TeacherCareerProgressionIDDataModel> {

    Optional<TeacherCareerProgressionDataModel> findTopByTeacherIdOrderByDateDesc(String teacherID);

}
