package PAI.repository.courseInStudyPlanRepository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

import java.util.List;

public interface ICourseInStudyPlanDDDRepository extends IRepository<CourseInStudyPlanID, CourseInStudyPlan> {

    boolean createCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID);
    List<CourseInStudyPlan> getAllCourseInStudyPlanList_2();

    List<CourseInStudyPlan> getCoursesInStudyPlanByStudyPlanID(StudyPlanID studyPlanID);
}
