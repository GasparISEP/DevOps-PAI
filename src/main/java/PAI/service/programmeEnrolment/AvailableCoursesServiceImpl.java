package PAI.service.programmeEnrolment;

import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;

public class AvailableCoursesServiceImpl {
    private final ICourseEditionRepository _courseEditionRepository;
    private final ICourseInStudyPlanRepository _courseInStudyPlanRepository;

    public AvailableCoursesServiceImpl(ICourseEditionRepository courseEditionRepository, ICourseInStudyPlanRepository courseInStudyPlanRepository){
        if ( courseEditionRepository == null || courseInStudyPlanRepository == null) throw new IllegalArgumentException("Cannot be null");
        _courseEditionRepository = courseEditionRepository;
        _courseInStudyPlanRepository = courseInStudyPlanRepository;
    }
}
