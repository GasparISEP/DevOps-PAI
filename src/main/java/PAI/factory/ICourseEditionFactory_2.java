package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition_2;

public interface ICourseEditionFactory_2 {

    CourseEdition_2 newCourseEdition_2 (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception;

    CourseEdition_2 newCourseEdition_2 (CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception;

}


