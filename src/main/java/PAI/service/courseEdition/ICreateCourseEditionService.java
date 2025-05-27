package PAI.service.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.studyPlan.StudyPlan;

import java.util.List;

public interface ICreateCourseEditionService {

    public CourseEdition createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID);

    public List<DegreeType> getAllDegreeTypes();

    public List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID id);

    public List<StudyPlan> getStudyPlansByProgrammeID(ProgrammeID programmeID);

    public StudyPlanID getLatestStudyPlanIDByProgrammeID(ProgrammeID programmeID);

    public List<CourseInStudyPlan> getCoursesByStudyPlanId(StudyPlanID studyPlanID) throws Exception;

    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID) throws Exception;

    Iterable<CourseEdition> findAll();
}