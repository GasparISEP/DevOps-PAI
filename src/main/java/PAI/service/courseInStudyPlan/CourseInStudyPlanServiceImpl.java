package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanAssembler;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseInStudyPlanServiceImpl implements ICourseInStudyPlanService {

    private final ICourseInStudyPlanRepository _repository;
    private final ICourseInStudyPlanFactory _factory;
    private final ICourseInStudyPlanAssembler _assembler;

    public CourseInStudyPlanServiceImpl(ICourseInStudyPlanRepository repository, ICourseInStudyPlanFactory factory, ICourseInStudyPlanAssembler assembler) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this._repository = repository;

        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        this._factory = factory;

        if (assembler == null) {
            throw new IllegalArgumentException("Assembler cannot be null");
        }
        this._assembler = assembler;
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

    @Override
    public List<CourseInStudyPlanServiceDTO> getCourseSummariesByStudyPlanID(StudyPlanID studyPlanID) {
        List<CourseInStudyPlanServiceDTO> result = new ArrayList<>();
        for (CourseInStudyPlan c : _repository.findAll()) {
            if (c.identity().getStudyPlanID().equals(studyPlanID)) {
                result.add(_assembler.toServiceDTO(c));
            }
        }
        return result;
    }

}
