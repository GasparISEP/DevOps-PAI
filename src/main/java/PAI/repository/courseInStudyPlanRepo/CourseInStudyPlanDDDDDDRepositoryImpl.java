package PAI.repository.courseInStudyPlanRepo;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDD;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanDDDFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseInStudyPlanDDDDDDRepositoryImpl implements ICourseInStudyPlanDDDRepository {

    private final ICourseInStudyPlanDDDFactory _courseInStudyPlanFactory_2;
    private final List<CourseInStudyPlanDDD> _coursesInStudyPlanList_2;

    public CourseInStudyPlanDDDDDDRepositoryImpl(ICourseInStudyPlanDDDFactory iCourseInStudyPlanDDDFactory_, ICourseInStudyPlanDDDListFactory iCourseInStudyPlanListFactory_2) {

        _courseInStudyPlanFactory_2 = iCourseInStudyPlanDDDFactory_;
        _coursesInStudyPlanList_2 = iCourseInStudyPlanListFactory_2.newArrayList();

    }

    public boolean createCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) {

        CourseInStudyPlanDDD courseInStudyPlan_DDD = _courseInStudyPlanFactory_2.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        if (_coursesInStudyPlanList_2.contains(courseInStudyPlan_DDD))
            return false;

        _coursesInStudyPlanList_2.add(courseInStudyPlan_DDD);
        return true;
    }

    public List<CourseInStudyPlanDDD> getAllCourseInStudyPlanList_2() {
        return _coursesInStudyPlanList_2;
    }

    @Override
    public List<CourseInStudyPlanDDD> getCoursesInStudyPlanByStudyPlanID(StudyPlanID studyPlanID) {
        List<CourseInStudyPlanDDD> listOfCoursesInStudyPlan = new ArrayList<>();
        for (CourseInStudyPlanDDD courseInStudyPlan : _coursesInStudyPlanList_2) {
            if (courseInStudyPlan.getStudyplanID().equals(studyPlanID))
                listOfCoursesInStudyPlan.add(courseInStudyPlan);
        }
        return listOfCoursesInStudyPlan;
    }

    @Override
    public CourseInStudyPlanDDD save(CourseInStudyPlanDDD courseInStudyPlanDDD) {
        _coursesInStudyPlanList_2.add(courseInStudyPlanDDD);
        return courseInStudyPlanDDD;
    }

    @Override
    public Iterable<CourseInStudyPlanDDD> findAll() {
        return _coursesInStudyPlanList_2;
    }

    @Override
    public Optional<CourseInStudyPlanDDD> ofIdentity(CourseInStudyPlanID id) {
        for (CourseInStudyPlanDDD existingCourseInStudyPlanDDD : _coursesInStudyPlanList_2) {
            if (existingCourseInStudyPlanDDD.identity().equals(id)) {
                return Optional.of(existingCourseInStudyPlanDDD);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(CourseInStudyPlanID id) {
        for (CourseInStudyPlanDDD existingCourseInStudyPlanDDD : _coursesInStudyPlanList_2) {
            if (existingCourseInStudyPlanDDD.identity().equals(id)) {
                return true;
            }
        }
        return false;
    }
}