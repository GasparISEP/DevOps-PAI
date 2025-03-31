package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.Course;
import PAI.domain.CourseEdition;
import PAI.domain.CourseEdition_2;
import PAI.domain.ProgrammeEdition;

public class CourseEditionFactoryImpl_2 implements ICourseEditionFactory_2 {

    public CourseEdition_2 newCourseEdition_2(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception {
        return new CourseEdition_2 (courseInStudyPlanID, programmeEditionID);
    }

    //auxiliary constructor
    public CourseEdition_2 newCourseEdition_2(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception {
        return new CourseEdition_2 (courseEditionID, courseInStudyPlanID, programmeEditionID);
    }

}
