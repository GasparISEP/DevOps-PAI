package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;

import java.util.List;
import java.util.Optional;

public interface ICourseInStudyPlanService {

    boolean createCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                    DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) throws Exception;

    List<CourseInStudyPlan> getAllCoursesInStudyPlan() throws Exception;

    List<CourseInStudyPlan> getCoursesByStudyPlanId(StudyPlanID studyPlanID) throws Exception;

    Optional<CourseInStudyPlan> findById(CourseInStudyPlanID courseInStudyPlanID);
}
