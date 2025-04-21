package PAI.repository.courseInStudyPlanRepository;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

import java.util.List;
import java.util.Optional;

public class CourseInStudyPlanRepositoryImpl implements ICourseInStudyPlanRepository {

    private final List<CourseInStudyPlan> _coursesInStudyPlanList;

    public CourseInStudyPlanRepositoryImpl(ICourseInStudyPlanListFactory iCourseInStudyPlanListFactory) {

        this._coursesInStudyPlanList = iCourseInStudyPlanListFactory.newArrayList();

    }

   /* public boolean createCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) {

        CourseInStudyPlan courseInStudyPlan_DDD = _courseInStudyPlanFactory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID);

        if (_coursesInStudyPlanList.contains(courseInStudyPlan_DDD))
            return false;

        _coursesInStudyPlanList.add(courseInStudyPlan_DDD);
        return true;
    }*/

    public List<CourseInStudyPlan> getAllCourseInStudyPlanList() {
        return _coursesInStudyPlanList;
    }

   /* @Override
    public List<CourseInStudyPlan> getCoursesInStudyPlanByStudyPlanID(StudyPlanID studyPlanID) {
        List<CourseInStudyPlan> listOfCoursesInStudyPlan = new ArrayList<>();
        for (CourseInStudyPlan courseInStudyPlan : _coursesInStudyPlanList) {
            if (courseInStudyPlan.getStudyplanID().equals(studyPlanID))
                listOfCoursesInStudyPlan.add(courseInStudyPlan);
        }
        return listOfCoursesInStudyPlan;
    }*/

    @Override
    public CourseInStudyPlan save(CourseInStudyPlan courseInStudyPlan) {
        _coursesInStudyPlanList.add(courseInStudyPlan);
        return courseInStudyPlan;
    }

    @Override
    public Iterable<CourseInStudyPlan> findAll() {
        return _coursesInStudyPlanList;
    }

    @Override
    public Optional<CourseInStudyPlan> ofIdentity(CourseInStudyPlanID id) {
        for (CourseInStudyPlan existingCourseInStudyPlan : _coursesInStudyPlanList) {
            if (existingCourseInStudyPlan.identity().equals(id)) {
                return Optional.of(existingCourseInStudyPlan);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(CourseInStudyPlanID id) {
        for (CourseInStudyPlan existingCourseInStudyPlan : _coursesInStudyPlanList) {
            if (existingCourseInStudyPlan.identity().equals(id)) {
                return true;
            }
        }
        return false;
    }
}