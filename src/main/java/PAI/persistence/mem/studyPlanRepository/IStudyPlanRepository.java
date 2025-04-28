package PAI.persistence.mem.studyPlanRepository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.studyPlan.StudyPlan;

import java.util.List;

public interface IStudyPlanRepository extends IRepository<StudyPlanID, StudyPlan> {

    boolean createStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts);
    List<StudyPlan> getAllStudyPlansByProgrammeId(ProgrammeID programmeID);
    List<StudyPlan> getAllStudyPlans_2();

    StudyPlanID getLatestStudyPlanIDByProgrammeID(ProgrammeID programmeID);
}