package PAI.factory;

import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.Course;
import PAI.domain.CourseEdition_2;
import PAI.domain.ProgrammeEdition;

public interface ICourseEditionFactory {
    CourseEdition_2 newCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception;
}
