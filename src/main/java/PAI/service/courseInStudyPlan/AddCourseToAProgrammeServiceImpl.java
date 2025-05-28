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
    public CourseInStudyPlanServiceDTO addCourseToAProgramme(CourseInStudyPlanCommand command) throws Exception {
        validateCommand(command);

        ProgrammeID programmeID = buildProgrammeID(command);
        StudyPlanID studyPlanID = getStudyPlanID(programmeID);

        CourseInStudyPlan courseInStudyPlan = buildCourseInStudyPlan(command, studyPlanID);

        validateBusinessRules(courseInStudyPlan, studyPlanID, command);

        CourseInStudyPlan saved = repository.save(courseInStudyPlan);

        return businessAssembler.toDTO(saved);
    }

    private void validateCommand(CourseInStudyPlanCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("CourseInStudyPlanCommand cannot be null.");
        }
    }

    private ProgrammeID buildProgrammeID(CourseInStudyPlanCommand command) {
        return new ProgrammeID(command.programmeName(), command.programmeAcronym());
    }

    private StudyPlanID getStudyPlanID(ProgrammeID programmeID) {
        StudyPlanID studyPlanID = studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID);
        if (studyPlanID == null) {
            throw new BusinessRuleViolationException("No study plan found for the given programme ID.");
        }
        return studyPlanID;
    }

    private CourseInStudyPlan buildCourseInStudyPlan(CourseInStudyPlanCommand command, StudyPlanID studyPlanID) {
        Semester semester = command.semester();
        CurricularYear curricularYear = command.curricularYear();
        CourseID courseID = new CourseID(command.courseAcronym(), command.courseName());
        DurationCourseInCurricularYear duration = command.duration();
        CourseQuantityCreditsEcts credits = command.credits();

        return factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, duration, credits);
    }

    private void validateBusinessRules(CourseInStudyPlan course, StudyPlanID studyPlanID, CourseInStudyPlanCommand command) {
        CourseInStudyPlanID courseInStudyPlanID = course.identity();
        if (repository.containsOfIdentity(courseInStudyPlanID)) {
            throw new BusinessRuleViolationException("This Course already exists in this StudyPlan.");
        }
        double totalCredits = repository.getTotalCreditsEctsInStudyPlanSoFar(
                studyPlanID, command.semester(), command.curricularYear(), command.duration()
        ) + command.credits().getQuantity();
        if (totalCredits > 30) {
            throw new BusinessRuleViolationException("This StudyPlan already has 30 ECTS credits.");
        }
    }
}

