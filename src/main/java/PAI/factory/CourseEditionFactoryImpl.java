package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;

public class CourseEditionFactoryImpl implements ICourseEditionFactory {

    public CourseEdition newCourseEdition_2(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
        return new CourseEdition(courseEditionID, courseInStudyPlanID, programmeEditionID);
    }

    @Override
    public CourseEdition newCourseEdition_2(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        return new CourseEdition(courseEditionID, courseInStudyPlanID, programmeEditionID);
    }

}
