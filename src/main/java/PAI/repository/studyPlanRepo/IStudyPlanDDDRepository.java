package PAI.repository.studyPlanRepo;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.studyPlan.StudyPlanDDD;

import java.util.Optional;

public interface IStudyPlanDDDRepository extends IRepository<StudyPlanID, StudyPlanDDD> {

    boolean createStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, QuantEcts quantityOfEcts);
}