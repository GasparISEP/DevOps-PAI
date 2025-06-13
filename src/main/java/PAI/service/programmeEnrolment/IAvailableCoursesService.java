package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

import java.util.List;

public interface IAvailableCoursesService {
    List<CourseEditionID> allCourseEditionIdsFromProgrammeEdition(ProgrammeEditionID programmeEditionID);
    List<CourseInStudyPlanID> allCoursesInStudyFromProgrammeEdition(List<CourseEditionID> courseEditionIDS);
    List<CourseInStudyPlan> getByIdentity(List<CourseInStudyPlanID> courseInStudyPlanIDS);
    List<CourseID> getListOfCoursesID(List<CourseInStudyPlan> courseInStudyPlans);
    List<CourseID> getListOfCourseIdForAGivenProgrammeEdition(ProgrammeEditionID programmeEditionID);
    List<AvailableCourseInfo> getListOfCourseInfo(List<CourseInStudyPlan> courseInStudyPlans);
    List<AvailableCourseInfo> getListOfAvailableCourseInfoForAGivenProgrammeEdition(ProgrammeEditionID programmeEditionID);
}
