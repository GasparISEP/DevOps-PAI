package PAI.persistence.springdata.studyPlan;

import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudyPlanRepositorySpringData extends JpaRepository<StudyPlanDataModel, StudyPlanIDDataModel> {

}
