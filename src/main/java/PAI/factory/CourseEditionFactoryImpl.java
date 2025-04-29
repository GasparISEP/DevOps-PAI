package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;

public class CourseEditionFactoryImpl implements ICourseEditionFactory {

    public CourseEdition createCourseEditionToDomain(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
        return new CourseEdition(courseEditionID, courseInStudyPlanID, programmeEditionID);
    }

    @Override
    public CourseEdition createCourseEditionFromDataModel(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID, TeacherID teacherID) {
        return new CourseEdition(courseEditionID, courseInStudyPlanID, programmeEditionID, teacherID);
    }
}
