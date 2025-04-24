package PAI.service.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;

import java.util.List;

public interface ICourseEditionService {

    public CourseEdition createAndSaveCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID);

    public Iterable<CourseEdition> findAll();

    public List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId);

    public CourseEdition ofIdentity(CourseEditionID courseEditionID);

    public boolean containsOfIdentity(CourseEditionID courseEditionID);
}
