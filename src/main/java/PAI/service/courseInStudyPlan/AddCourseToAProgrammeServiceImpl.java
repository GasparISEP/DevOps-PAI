package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.studyPlan.IStudyPlanService;
import org.springframework.stereotype.Service;

@Service
public class AddCourseToAProgrammeServiceImpl implements IAddCourseToAProgrammeService {

    private final IStudyPlanService studyPlanService;
    private final ICourseInStudyPlanRepository repository;
    private final ICourseInStudyPlanFactory factory;

    public AddCourseToAProgrammeServiceImpl(IStudyPlanService studyPlanService, ICourseInStudyPlanRepository repository, ICourseInStudyPlanFactory factory) {
        this.studyPlanService = studyPlanService;
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public CourseInStudyPlan addCourseToAProgramme(CourseInStudyPlanCommand courseInStudyPlanCommand) throws Exception {
        if (courseInStudyPlanCommand == null) {
            throw new IllegalArgumentException("CourseInStudyPlanCommand cannot be null.");
        }

        ProgrammeID programmeID = new ProgrammeID(
                new NameWithNumbersAndSpecialChars(courseInStudyPlanCommand.programmeName()),
                new Acronym(courseInStudyPlanCommand.programmeAcronym()));

        StudyPlanID studyPlanID = studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID);

        Semester semester = new Semester(courseInStudyPlanCommand.semester());
        CurricularYear curricularYear = new CurricularYear(courseInStudyPlanCommand.curricularYear());
        CourseID courseID = new CourseID(
                new Acronym(courseInStudyPlanCommand.courseAcronym()),
                new Name(courseInStudyPlanCommand.courseName()));

        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(courseInStudyPlanCommand.curricularYear());
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(courseInStudyPlanCommand.credits());

        if (studyPlanService.getLatestStudyPlanIDByProgrammeID(studyPlanID.getProgrammeID()) == null) {
            throw new BusinessRuleViolationException("No study plan found for the given programme ID.");
        }

        CourseInStudyPlan courseInStudyPlan = factory.newCourseInStudyPlan(
                semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts);

        CourseInStudyPlanID courseInStudyPlanID = courseInStudyPlan.identity();

        if (repository.containsOfIdentity(courseInStudyPlanID)) {
            throw new BusinessRuleViolationException("This Course already exists in this StudyPlan.");
        }
        if (repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse) + quantityOfCreditsEcts.getQuantity() > 30) {
            throw new BusinessRuleViolationException("This StudyPlan already has 30 ECTS credits.");
        }

        return repository.save(courseInStudyPlan);
    }

}

