package PAI.repository;

import PAI.VOs.*;
import PAI.domain.CourseInStudyPlan_2;
import PAI.factory.ICourseInStudyPlanFactory_2;
import PAI.factory.ICourseInStudyPlanListFactory_2;

import java.util.List;
import java.util.Optional;

public class CourseInStudyPlanRepository_2 {

    private final ICourseInStudyPlanFactory_2 _courseInStudyPlanFactory_2;
    private final List<CourseInStudyPlan_2> _coursesInStudyPlanList_2;

    public CourseInStudyPlanRepository_2(ICourseInStudyPlanFactory_2 iCourseInStudyPlanFactory_2, ICourseInStudyPlanListFactory_2 iCourseInStudyPlanListFactory_2) {

        _courseInStudyPlanFactory_2 = iCourseInStudyPlanFactory_2;
        _coursesInStudyPlanList_2 = iCourseInStudyPlanListFactory_2.newArrayList();

    }

    public boolean createCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) throws Exception {

        CourseInStudyPlan_2 courseInStudyPlan_2 = _courseInStudyPlanFactory_2.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        if (_coursesInStudyPlanList_2.contains(courseInStudyPlan_2))
            return false;

        _coursesInStudyPlanList_2.add(courseInStudyPlan_2);
        return true;
    }

    public List<CourseInStudyPlan_2> getAllCourseInStudyPlanList_2() {
        return _coursesInStudyPlanList_2;
    }


    public Optional<CourseInStudyPlan_2> findByCourseInStudyPlanID(CourseInStudyPlanID courseInStudyPlanID) {
        return _coursesInStudyPlanList_2.stream()
                .filter(courseInStudyPlan_2 -> courseInStudyPlan_2.getCourseInStudyPlanID().equals(courseInStudyPlanID))
                .findFirst();
    }
}
