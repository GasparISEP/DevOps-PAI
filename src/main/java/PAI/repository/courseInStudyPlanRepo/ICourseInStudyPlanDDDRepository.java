package PAI.repository.courseInStudyPlanRepo;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDD;

import java.util.List;

public interface ICourseInStudyPlanDDDRepository extends IRepository<CourseInStudyPlanID, CourseInStudyPlanDDD> {

    boolean createCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID);
    List<CourseInStudyPlanDDD> getAllCourseInStudyPlanList_2();
}
