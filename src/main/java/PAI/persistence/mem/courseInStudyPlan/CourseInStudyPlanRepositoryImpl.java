package PAI.persistence.mem.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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

    @Override
    public double getTotalCreditsEctsInStudyPlanSoFar(
            StudyPlanID studyPlanID,
            Semester semester,
            CurricularYear curricularYear,
            DurationCourseInCurricularYear duration) {

        Iterable<CourseInStudyPlan> allCourses = findAll();

        return StreamSupport.stream(allCourses.spliterator(), false)
                .filter(c -> c.getStudyplanID().equals(studyPlanID))
                .filter(c -> c.getCurricularYear().equals(curricularYear))
                .mapToDouble(c -> {
                    if (c.getDurationOfCourse().getDuration() == 1
                            && c.getSemester().equals(semester)) {
                        return c.getQuantityOfCreditsEcts().getQuantity();
                    }
                    if (c.getDurationOfCourse().getDuration() == 2
                            && c.getSemester().toInt() == 1) {
                        return c.getQuantityOfCreditsEcts().getQuantity() / 2.0;
                    }
                    return 0.0;
                })
                .sum();
    }
}