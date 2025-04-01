package PAI.factory;

import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition_2;

public class CourseEditionFactoryImpl implements ICourseEditionFactory {

    public CourseEdition_2 newCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception {
        return new CourseEdition_2(courseInStudyPlanID, programmeEditionID);
    }
}
