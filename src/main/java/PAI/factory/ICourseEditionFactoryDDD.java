package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEditionDDD;

public interface ICourseEditionFactoryDDD {

    CourseEditionDDD newCourseEdition_2 (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception;

    CourseEditionDDD newCourseEdition_2 (CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception;

}


