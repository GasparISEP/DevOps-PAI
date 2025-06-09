package PAI.service.programmeEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

import java.util.List;

public interface IAvailableCoursesService {
    List<CourseEditionID> allCourseEditionIdsFromProgrammeEdition(ProgrammeEditionID programmeEditionID);
    List<CourseInStudyPlanID> allCoursesInStudyFromProgrammeEdition(List<CourseEditionID> courseEditionIDS);
    List<CourseInStudyPlan> getByIdentity(List<CourseInStudyPlanID> courseInStudyPlanIDS);
    List<CourseID> getListOfCoursesFromACurrentCurricularYear(List<CourseInStudyPlan> courseInStudyPlans);
    List<CourseID> getListOfCourseIdForAGivenProgrammeEditionAndInASpecificCurricularYear(ProgrammeEditionID programmeEditionID);
}
