package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanBusinessAssembler;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.domain.repositoryInterfaces.studyPlan.IStudyPlanRepository;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.exception.AlreadyExistsException;
import PAI.exception.NotFoundException;
import PAI.exception.CreditsExceededException;
import org.springframework.stereotype.Service;

import static PAI.utils.ValidationUtils.validateNotNull;

@Service
public class AddCourseToAProgrammeServiceImpl implements IAddCourseToAProgrammeService {

    private final IStudyPlanRepository studyPlanRepository;
    private final ICourseInStudyPlanRepository repository;
    private final ICourseInStudyPlanFactory factory;
    private final ICourseInStudyPlanBusinessAssembler businessAssembler;

    public AddCourseToAProgrammeServiceImpl(IStudyPlanRepository studyPlanRepository, ICourseInStudyPlanRepository repository,
                                            ICourseInStudyPlanFactory factory, ICourseInStudyPlanBusinessAssembler businessAssembler) {
        this.studyPlanRepository = validateNotNull(studyPlanRepository, "StudyPlanRepository");
        this.repository = validateNotNull(repository, "CourseInStudyPlanRepository");
        this.factory = validateNotNull(factory, "CourseInStudyPlanFactory");
        this.businessAssembler = validateNotNull(businessAssembler, "CourseInStudyPlanBusinessAssembler");
    }

    @Override
    public CourseInStudyPlanServiceDTO addCourseToAProgramme(CourseInStudyPlanCommand command) throws Exception {
        validateNotNull(command, "CourseInStudyPlanCommand");

        ProgrammeID programmeID = buildProgrammeID(command);
        StudyPlanID studyPlanID = getStudyPlanID(programmeID);

        CourseInStudyPlan courseInStudyPlan = buildCourseInStudyPlan(command, studyPlanID);

        validateBusinessRules(courseInStudyPlan, studyPlanID, command);

        CourseInStudyPlan saved = repository.save(courseInStudyPlan);

        return businessAssembler.toDTO(saved);
    }

    private ProgrammeID buildProgrammeID(CourseInStudyPlanCommand command) {
        return new ProgrammeID(command.programmeAcronym());
    }

    private StudyPlanID getStudyPlanID(ProgrammeID programmeID) {
        StudyPlanID studyPlanID = studyPlanRepository.findLatestByProgrammeID(programmeID);
        if (studyPlanID == null) {
            throw new NotFoundException("No study plan found for the given programme ID.");
        }
        return studyPlanID;
    }

    private CourseInStudyPlan buildCourseInStudyPlan(CourseInStudyPlanCommand command, StudyPlanID studyPlanID) {
        Semester semester = command.semester();
        CurricularYear curricularYear = command.curricularYear();
        CourseID courseID = new CourseID(command.courseAcronym(), command.courseName());
        DurationCourseInCurricularYear duration = command.duration();
        CourseQuantityCreditsEcts credits = command.credits();
        ProgrammeID programmeID = new ProgrammeID(new Acronym(command.programmeAcronym().getAcronym()));

        return factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, duration, credits, programmeID);
    }

    private void validateBusinessRules(CourseInStudyPlan course, StudyPlanID studyPlanID, CourseInStudyPlanCommand command) {
        CourseInStudyPlanID courseInStudyPlanID = course.identity();
        if (repository.containsOfIdentity(courseInStudyPlanID)) {
            throw new AlreadyExistsException("This Course already exists in this StudyPlan.");
        }
        double totalCredits = repository.getTotalCreditsEctsInStudyPlanSoFar(
                studyPlanID, command.semester(), command.curricularYear(), command.duration()
        ) + command.credits().getQuantity();
        if (totalCredits > 30) {
            throw new CreditsExceededException("This StudyPlan already has 30 ECTS credits.");
        }
    }
}