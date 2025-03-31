package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.Course;
import PAI.domain.CourseEdition;
import PAI.domain.CourseEdition_2;
import PAI.domain.ProgrammeEdition;

public class CourseEditionFactoryImpl_2 implements ICourseEditionFactory_2 {

    public CourseEdition_2 newCourseEdition_2(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
        return new CourseEdition_2 (courseEditionID, courseInStudyPlanID, programmeEditionID);
    }

    @Override
    public CourseEdition_2 newCourseEdition_2(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        return new CourseEdition_2 (courseEditionID, courseInStudyPlanID, programmeEditionID);
    }

}
