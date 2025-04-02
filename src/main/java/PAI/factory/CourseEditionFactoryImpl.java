package PAI.factory;

import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEditionDDD;

public class CourseEditionFactoryImpl implements ICourseEditionFactory {

    public CourseEditionDDD newCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception {
        return new CourseEditionDDD(courseInStudyPlanID, programmeEditionID);
    }
}
