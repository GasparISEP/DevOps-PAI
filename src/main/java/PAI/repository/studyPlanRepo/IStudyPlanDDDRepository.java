package PAI.repository.studyPlanRepo;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.studyPlan.StudyPlanDDD;

import java.util.List;
import java.util.Optional;

public interface IStudyPlanDDDRepository extends IRepository<StudyPlanID, StudyPlanDDD> {

    boolean createStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, QuantEcts quantityOfEcts);
    List<StudyPlanDDD> getAllStudyPlansByProgrammeId(ProgrammeID programmeID);
    List<StudyPlanDDD> getAllStudyPlans_2();

    StudyPlanID getLatestStudyPlanIDByProgrammeID(ProgrammeID programmeID);
}