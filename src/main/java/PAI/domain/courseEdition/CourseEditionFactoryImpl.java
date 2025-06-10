package PAI.domain.courseEdition;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class CourseEditionFactoryImpl implements ICourseEditionFactory {

    public CourseEdition createCourseEditionToDomain(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
        CourseEditionGeneratedID courseEditionGeneratedID= new CourseEditionGeneratedID();
        return new CourseEdition(courseEditionID, courseInStudyPlanID, programmeEditionID, courseEditionGeneratedID);
    }

    @Override
    public CourseEdition createCourseEditionFromDataModel(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID, CourseEditionGeneratedID courseEditionGeneratedID, TeacherID teacherID) {
        return new CourseEdition(courseEditionID, courseInStudyPlanID, programmeEditionID, courseEditionGeneratedID, teacherID);
    }
}
