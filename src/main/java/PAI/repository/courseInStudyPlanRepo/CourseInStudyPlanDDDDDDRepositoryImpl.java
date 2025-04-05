package PAI.repository.courseInStudyPlanRepo;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseInStudyPlanDDDDDDRepositoryImpl implements ICourseInStudyPlanDDDRepository {

    private final ICourseInStudyPlanFactory _courseInStudyPlanFactory_2;
    private final List<CourseInStudyPlan> _coursesInStudyPlanList_2;

    public CourseInStudyPlanDDDDDDRepositoryImpl(ICourseInStudyPlanFactory iCourseInStudyPlanFactory_, ICourseInStudyPlanDDDListFactory iCourseInStudyPlanListFactory_2) {

        _courseInStudyPlanFactory_2 = iCourseInStudyPlanFactory_;
        _coursesInStudyPlanList_2 = iCourseInStudyPlanListFactory_2.newArrayList();

    }

    public boolean createCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) {

        CourseInStudyPlan courseInStudyPlan_DDD = _courseInStudyPlanFactory_2.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        if (_coursesInStudyPlanList_2.contains(courseInStudyPlan_DDD))
            return false;

        _coursesInStudyPlanList_2.add(courseInStudyPlan_DDD);
        return true;
    }

    public List<CourseInStudyPlan> getAllCourseInStudyPlanList_2() {
        return _coursesInStudyPlanList_2;
    }

    @Override
    public List<CourseInStudyPlan> getCoursesInStudyPlanByStudyPlanID(StudyPlanID studyPlanID) {
        List<CourseInStudyPlan> listOfCoursesInStudyPlan = new ArrayList<>();
        for (CourseInStudyPlan courseInStudyPlan : _coursesInStudyPlanList_2) {
            if (courseInStudyPlan.getStudyplanID().equals(studyPlanID))
                listOfCoursesInStudyPlan.add(courseInStudyPlan);
        }
        return listOfCoursesInStudyPlan;
    }

    @Override
    public CourseInStudyPlan save(CourseInStudyPlan courseInStudyPlan) {
        _coursesInStudyPlanList_2.add(courseInStudyPlan);
        return courseInStudyPlan;
    }

    @Override
    public Iterable<CourseInStudyPlan> findAll() {
        return _coursesInStudyPlanList_2;
    }

    @Override
    public Optional<CourseInStudyPlan> ofIdentity(CourseInStudyPlanID id) {
        for (CourseInStudyPlan existingCourseInStudyPlan : _coursesInStudyPlanList_2) {
            if (existingCourseInStudyPlan.identity().equals(id)) {
                return Optional.of(existingCourseInStudyPlan);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(CourseInStudyPlanID id) {
        for (CourseInStudyPlan existingCourseInStudyPlan : _coursesInStudyPlanList_2) {
            if (existingCourseInStudyPlan.identity().equals(id)) {
                return true;
            }
        }
        return false;
    }
}