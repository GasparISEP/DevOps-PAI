package PAI.service.student;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.service.programmeEnrolment.IAvailableCoursesService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ProgrammeEditionEnrolment createProgrammeEditionEnrolment(StudentID studentID, ProgrammeEditionID programmeEditionID, Date date, EnrolmentStatus status) {
        return _enrolmentFactory.createWithEnrolmentDate(studentID, programmeEditionID, date, status);
    }

    private boolean isCourseIdInCourseInStudyPlan(CourseID courseID, CourseInStudyPlan studyPlan){
        return studyPlan.getCourseID().equals(courseID);
    }

    private boolean isCourseIdInListOfCourseInStudyPlan(List<CourseID> list, CourseInStudyPlan csp) {
        for (CourseID existingCourseID : list) {
            if (isCourseIdInCourseInStudyPlan(existingCourseID, csp)) {
                return true;
            }
        }
        return false;
    }

}
