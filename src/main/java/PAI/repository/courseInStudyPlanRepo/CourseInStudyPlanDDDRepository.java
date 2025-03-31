package PAI.repository.courseInStudyPlanRepo;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDD;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanDDDFactory;

import java.util.List;
import java.util.Optional;

public class CourseInStudyPlanDDDRepository {

    private final ICourseInStudyPlanDDDFactory _courseInStudyPlanFactory_2;
    private final List<CourseInStudyPlanDDD> _coursesInStudyPlanList_2;

    public CourseInStudyPlanDDDRepository(ICourseInStudyPlanDDDFactory iCourseInStudyPlanDDDFactory_, ICourseInStudyPlanDDDListFactory iCourseInStudyPlanListFactory_2) {

        _courseInStudyPlanFactory_2 = iCourseInStudyPlanDDDFactory_;
        _coursesInStudyPlanList_2 = iCourseInStudyPlanListFactory_2.newArrayList();

    }

    public boolean createCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) throws Exception {

        CourseInStudyPlanDDD courseInStudyPlan_DDD = _courseInStudyPlanFactory_2.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        if (_coursesInStudyPlanList_2.contains(courseInStudyPlan_DDD))
            return false;

        _coursesInStudyPlanList_2.add(courseInStudyPlan_DDD);
        return true;
    }

    public List<CourseInStudyPlanDDD> getAllCourseInStudyPlanList_2() {
        return _coursesInStudyPlanList_2;
    }


    public Optional<CourseInStudyPlanDDD> findByCourseInStudyPlanID(CourseInStudyPlanID courseInStudyPlanID) {
        return _coursesInStudyPlanList_2.stream()
                .filter(courseInStudyPlan_2 -> courseInStudyPlan_2.getCourseInStudyPlanID().equals(courseInStudyPlanID))
                .findFirst();
    }
}
