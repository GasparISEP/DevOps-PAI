package PAI.persistence.springdata.studyPlan;

import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IStudyPlanRepositorySpringData extends JpaRepository<StudyPlanDataModel, StudyPlanIDDataModel> {

    Optional<StudyPlanDataModel> findByUuid(UUID studyPlanUUID);
}
