package PAI.domain.courseEdition;

import PAI.VOs.*;

public interface ICourseEditionFactory {

    CourseEdition createCourseEditionToDomain(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception;

    CourseEdition createCourseEditionFromDataModel(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID, CourseEditionGeneratedID courseEditionGeneratedID, TeacherID teacherID) throws Exception;

}


