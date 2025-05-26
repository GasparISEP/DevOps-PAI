package PAI.service.courseInStudyPlan;

import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanAssembler;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.exception.BusinessRuleViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseInStudyPlanServiceImpl implements ICourseInStudyPlanService {

    private final ICourseInStudyPlanRepository _repository;
    private final ICourseInStudyPlanFactory _factory;

    public CourseInStudyPlanServiceImpl(ICourseInStudyPlanRepository repository, ICourseInStudyPlanFactory factory) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this._repository = repository;

        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        this._factory = factory;
    }

    public boolean createCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                           DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) throws Exception {

        CourseInStudyPlan courseInStudyPlan = _factory.newCourseInStudyPlan(
                semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts);

        CourseInStudyPlanID courseInStudyPlanID = courseInStudyPlan.identity();

        if (_repository.containsOfIdentity(courseInStudyPlanID)) {
            return false;
        }
        if (_repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse) + quantityOfCreditsEcts.getQuantity() > 30) {
            return false;
        }

        _repository.save(courseInStudyPlan);
        return true;
    }

    public CourseInStudyPlan createCourseInStudyPlan(CourseInStudyPlanCommand command) throws Exception {
        if (command == null){
            throw new IllegalArgumentException("command cannot be null!");
        }
        Semester semester = new Semester(command.semester());
        CurricularYear curricularYear = new CurricularYear(command.curricularYear());
        Acronym courseAcronym = new Acronym(command.courseAcronym());
        Name courseName = new Name(command.courseName());
        CourseID courseID = new CourseID(courseAcronym, courseName);
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(command.programmeName());
        Acronym programmeAcronym = new Acronym(command.programmeAcronym());
        ProgrammeID programmeID = new ProgrammeID(programmeName,programmeAcronym);
        PAI.VOs.Date customDate = new PAI.VOs.Date(command.studyPlanDate());
        StudyPlanID studyPlanID = new StudyPlanID(programmeID,customDate);

        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(command.duration());
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(command.credits());

        CourseInStudyPlan courseInStudyPlan = _factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, duration, quantityOfCreditsEcts);

        CourseInStudyPlanID courseInStudyPlanID = courseInStudyPlan.identity();
        if (_repository.containsOfIdentity(courseInStudyPlanID)) {
            throw new BusinessRuleViolationException("CourseInStudyPlan already exists");
        }
        double totalCredits = _repository.getTotalCreditsEctsInStudyPlanSoFar(
                studyPlanID, semester, curricularYear, duration
        );

        if (totalCredits + quantityOfCreditsEcts.getQuantity() > 30) {
            throw new IllegalArgumentException("The total of ECTS credits exceed the limit of 30");
        }
        _repository.save(courseInStudyPlan);
        return courseInStudyPlan;
    }

    public List<CourseInStudyPlan> getAllCoursesInStudyPlan() throws Exception {
        List<CourseInStudyPlan> resultado = new ArrayList<>();
        for (CourseInStudyPlan c : _repository.findAll()) {
            resultado.add(c);
        }
        return resultado;
    }


    public List<CourseInStudyPlan> getCoursesByStudyPlanId(StudyPlanID studyPlanID) throws Exception {
        List<CourseInStudyPlan> resultado = new ArrayList<>();
        for (CourseInStudyPlan c : _repository.findAll()) {
            if (c.identity().getStudyPlanID().equals(studyPlanID)) {
                resultado.add(c);
            }
        }
        return resultado;
    }


    public Optional<CourseInStudyPlan> findById(CourseInStudyPlanID id) {
        return _repository.ofIdentity(id);
    }
}
