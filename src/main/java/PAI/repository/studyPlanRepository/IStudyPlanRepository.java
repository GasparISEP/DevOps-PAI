package PAI.repository.studyPlanRepository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.studyPlan.StudyPlan;

import java.util.List;

public interface IStudyPlanRepository extends IRepository<StudyPlanID, StudyPlan> {

    boolean createStudyPlan(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts);

    List<StudyPlan> getAllStudyPlansByProgrammeId(ProgrammeID programmeID);
    List<StudyPlan> getAllStudyPlans();

    StudyPlanID getLatestStudyPlanIDByProgrammeID(ProgrammeID programmeID);
}