package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;

import java.util.List;
import java.util.Optional;

public interface ICourseInStudyPlanService {

    boolean createCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                    DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts, ProgrammeID programmeID) throws Exception;

    List<CourseInStudyPlan> getAllCoursesInStudyPlan() throws Exception;

    List<CourseInStudyPlan> getCoursesByStudyPlanId(StudyPlanID studyPlanID) throws Exception;

    Optional<CourseInStudyPlan> findById(CourseInStudyPlanID courseInStudyPlanID);

    List<CourseInStudyPlanServiceDTO> getCourseSummariesByStudyPlanID(StudyPlanID studyPlanID);

    List<CourseInStudyPlan> getCoursesByProgrammeID(ProgrammeID programmeID);
}
