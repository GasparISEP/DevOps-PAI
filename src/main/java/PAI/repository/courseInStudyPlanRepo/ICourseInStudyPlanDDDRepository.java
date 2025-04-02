package PAI.repository.courseInStudyPlanRepo;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDD;

public interface ICourseInStudyPlanDDDRepository extends IRepository<CourseInStudyPlanID, CourseInStudyPlanDDD> {

    boolean createCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID);
}
