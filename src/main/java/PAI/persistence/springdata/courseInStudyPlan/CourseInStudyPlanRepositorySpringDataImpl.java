package PAI.persistence.springdata.courseInStudyPlan;

import PAI.VOs.CourseInStudyPlanID;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanMapper;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;

import java.util.Optional;

public class CourseInStudyPlanRepositorySpringDataImpl implements ICourseInStudyPlanRepository {

    private final ICourseInStudyPlanMapper _courseInStudyPlanMapper;
    private final ICourseInStudyPlanRepositorySpringData _courseInStudyPlanRepositorySpringData;

    public CourseInStudyPlanRepositorySpringDataImpl(ICourseInStudyPlanMapper courseInStudyPlanMapper, ICourseInStudyPlanRepositorySpringData courseInStudyPlanRepositorySpringData) {

        if (courseInStudyPlanMapper == null) {
            throw new IllegalArgumentException("courseInStudyPlanMapper cannot be null");
        }
        _courseInStudyPlanMapper = courseInStudyPlanMapper;

        if (courseInStudyPlanRepositorySpringData == null) {
            throw new IllegalArgumentException("courseInStudyPlanRepositorySpringData cannot be null");
        }
        _courseInStudyPlanRepositorySpringData = courseInStudyPlanRepositorySpringData;
    }



    @Override
    public CourseInStudyPlan save(CourseInStudyPlan entity) {
        return null;
    }

    @Override
    public Iterable<CourseInStudyPlan> findAll() {
        return null;
    }

    @Override
    public Optional<CourseInStudyPlan> ofIdentity(CourseInStudyPlanID id) {
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(CourseInStudyPlanID id) {
        return false;
    }
}