package PAI.domain.courseEdition;

import PAI.VOs.*;

public interface ICourseEditionFactory {

    CourseEdition createCourseEditionToDomain(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID);

    CourseEdition createCourseEditionFromDataModel(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID, CourseEditionGeneratedID courseEditionGeneratedID, TeacherID teacherID) ;

}


