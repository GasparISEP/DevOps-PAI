package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEditionDDD;

public class CourseEditionFactoryImplDDD implements ICourseEditionFactoryDDD {

    public CourseEditionDDD newCourseEdition_2(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
        return new CourseEditionDDD(courseEditionID, courseInStudyPlanID, programmeEditionID);
    }

    @Override
    public CourseEditionDDD newCourseEdition_2(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        return new CourseEditionDDD(courseEditionID, courseInStudyPlanID, programmeEditionID);
    }

}
