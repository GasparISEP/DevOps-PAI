package PAI.domain.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.TeacherID;

public interface ICourseEditionFactory {

    CourseEdition createCourseEditionToDomain(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception;

    CourseEdition createCourseEditionFromDataModel(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID, TeacherID teacherID) throws Exception;

}


