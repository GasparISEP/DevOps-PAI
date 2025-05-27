package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanBusinessAssembler;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.studyPlan.IStudyPlanService;
import org.springframework.stereotype.Service;

@Service
public class AddCourseToAProgrammeServiceImpl implements IAddCourseToAProgrammeService {

    private final IStudyPlanService studyPlanService;
    private final ICourseInStudyPlanRepository repository;
    private final ICourseInStudyPlanFactory factory;
    private final ICourseInStudyPlanBusinessAssembler businessAssembler;

    public AddCourseToAProgrammeServiceImpl(IStudyPlanService studyPlanService, ICourseInStudyPlanRepository repository,
                                            ICourseInStudyPlanFactory factory, ICourseInStudyPlanBusinessAssembler businessAssembler) {
        this.studyPlanService = studyPlanService;
        this.repository = repository;
        this.factory = factory;
        this.businessAssembler = businessAssembler;
    }

    @Override
    public CourseInStudyPlanServiceDTO addCourseToAProgramme(CourseInStudyPlanCommand courseInStudyPlanCommand) throws Exception {
        if (courseInStudyPlanCommand == null) {
            throw new IllegalArgumentException("CourseInStudyPlanCommand cannot be null.");
        }

        ProgrammeID programmeID = new ProgrammeID(
                courseInStudyPlanCommand.programmeName(),
                courseInStudyPlanCommand.programmeAcronym()
        );

        StudyPlanID studyPlanID = studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID);

        Semester semester = courseInStudyPlanCommand.semester();
        CurricularYear curricularYear = courseInStudyPlanCommand.curricularYear();
        CourseID courseID = new CourseID(
                courseInStudyPlanCommand.courseAcronym(),
                courseInStudyPlanCommand.courseName()
        );

        DurationCourseInCurricularYear durationOfCourse = courseInStudyPlanCommand.duration();
        CourseQuantityCreditsEcts quantityOfCreditsEcts = courseInStudyPlanCommand.credits();

        if (studyPlanID == null) {
            throw new BusinessRuleViolationException("No study plan found for the given programme ID.");
        }

        CourseInStudyPlan courseInStudyPlan = factory.newCourseInStudyPlan(
                semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts
        );

        CourseInStudyPlanID courseInStudyPlanID = courseInStudyPlan.identity();

        if (repository.containsOfIdentity(courseInStudyPlanID)) {
            throw new BusinessRuleViolationException("This Course already exists in this StudyPlan.");
        }
        if (repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse) + quantityOfCreditsEcts.getQuantity() > 30) {
            throw new BusinessRuleViolationException("This StudyPlan already has 30 ECTS credits.");
        }

        CourseInStudyPlan courseInStudyPlanSave = repository.save(courseInStudyPlan);

        return businessAssembler.toDTO(courseInStudyPlanSave);
    }

}

