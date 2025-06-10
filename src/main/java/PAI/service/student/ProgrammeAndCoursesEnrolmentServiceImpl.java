package PAI.service.student;

import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.service.programmeEnrolment.IAvailableCoursesService;

public class ProgrammeAndCoursesEnrolmentServiceImpl {
    private final IProgrammeEditionEnrolmentFactory _enrolmentFactory;
    private final IProgrammeEditionEnrolmentRepository _enrolmentRepository;
    private final ICourseEditionEnrolmentFactory _courseEditionEnrolmentFactory;
    private final ICourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;
    private final IAvailableCoursesService _availableCoursesService;
    private final ICourseEditionRepository _courseEditionRepository;

    public ProgrammeAndCoursesEnrolmentServiceImpl(IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory, IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
                                                   ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory, ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository, IAvailableCoursesService availableCoursesService,ICourseEditionRepository courseEditionRepository) {
        if (programmeEditionEnrolmentFactory == null || programmeEditionEnrolmentRepository == null || courseEditionEnrolmentFactory == null || courseEditionEnrolmentRepository == null || availableCoursesService == null || courseEditionRepository == null)
            throw new IllegalArgumentException("Cannot be null");
        _enrolmentFactory = programmeEditionEnrolmentFactory;
        _enrolmentRepository = programmeEditionEnrolmentRepository;
        _courseEditionEnrolmentFactory = courseEditionEnrolmentFactory;
        _courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
        _availableCoursesService = availableCoursesService;
        _courseEditionRepository = courseEditionRepository;
    }

}
