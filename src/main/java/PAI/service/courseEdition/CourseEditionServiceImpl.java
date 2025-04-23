package PAI.service.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;

import java.util.List;

public class CourseEditionServiceImpl implements ICourseEditionService {

    public CourseEdition createCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        return null;
    }

    public Iterable<CourseEdition> findAll() {
        return null;
    }

    public List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId) {
        return null;
    }

    public CourseEdition ofIdentity(CourseEditionID courseEditionID) {
        return null;
    }

    public boolean containsOfIdentity(CourseEditionID courseEditionID) {
        return false;
    }
}
