package PAI.persistence.mem.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;

import java.util.List;
import java.util.Optional;

public class CourseInStudyPlanRepositoryImpl implements ICourseInStudyPlanRepository {

    private final List<CourseInStudyPlan> _coursesInStudyPlanList;

    public CourseInStudyPlanRepositoryImpl(ICourseInStudyPlanListFactory iCourseInStudyPlanListFactory) {

        this._coursesInStudyPlanList = iCourseInStudyPlanListFactory.newArrayList();

    }

    public List<CourseInStudyPlan> getAllCourseInStudyPlanList() {
        return _coursesInStudyPlanList;
    }

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