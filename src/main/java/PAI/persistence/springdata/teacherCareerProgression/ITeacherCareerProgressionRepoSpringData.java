package PAI.persistence.springdata.teacherCareerProgression;

import PAI.persistence.datamodel.teacherCareer.TeacherCareerProgressionDataModel;
import PAI.persistence.datamodel.teacherCareer.TeacherCareerProgressionIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITeacherCareerProgressionRepoSpringData extends JpaRepository <TeacherCareerProgressionDataModel, TeacherCareerProgressionIDDataModel> {

    Optional<TeacherCareerProgressionDataModel> findTopByTeacherIdOrderByDateDesc(String teacherID);

}
