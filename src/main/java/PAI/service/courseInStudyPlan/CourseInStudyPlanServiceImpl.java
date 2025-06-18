package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanServiceAssembler;
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

    private final ICourseInStudyPlanRepository repository;
    private final ICourseInStudyPlanFactory factory;
    private final ICourseInStudyPlanServiceAssembler assembler;

    public CourseInStudyPlanServiceImpl(ICourseInStudyPlanRepository repository, ICourseInStudyPlanFactory factory, ICourseInStudyPlanServiceAssembler assembler) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.repository = repository;

        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        this.factory = factory;

        if (assembler == null) {
            throw new IllegalArgumentException("Assembler cannot be null");
        }
        this.assembler = assembler;
    }

    public boolean createCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                           DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts, ProgrammeID programmeID) throws Exception {

        CourseInStudyPlan courseInStudyPlan = factory.newCourseInStudyPlan(
                semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts, programmeID);

        CourseInStudyPlanID courseInStudyPlanID = courseInStudyPlan.identity();

        if (repository.containsOfIdentity(courseInStudyPlanID)) {
            return false;
        }
        if (repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse) + quantityOfCreditsEcts.getQuantity() > 30) {
            return false;
        }

        repository.save(courseInStudyPlan);
        return true;
    }

    public List<CourseInStudyPlan> getAllCoursesInStudyPlan() throws Exception {
        List<CourseInStudyPlan> resultado = new ArrayList<>();
        for (CourseInStudyPlan c : repository.findAll()) {
            resultado.add(c);
        }
        return resultado;
    }


    public List<CourseInStudyPlan> getCoursesByStudyPlanId(StudyPlanID studyPlanID) throws Exception {
        List<CourseInStudyPlan> resultado = new ArrayList<>();
        for (CourseInStudyPlan c : repository.findAll()) {
            if (c.identity().getStudyPlanID().equals(studyPlanID)) {
                resultado.add(c);
            }
        }
        return resultado;
    }


    public Optional<CourseInStudyPlan> findById(CourseInStudyPlanID id) {
        return repository.ofIdentity(id);
    }

    @Override
    public List<CourseInStudyPlanServiceDTO> getCourseSummariesByStudyPlanID(StudyPlanID studyPlanID) {
        List<CourseInStudyPlanServiceDTO> result = new ArrayList<>();
        for (CourseInStudyPlan c : repository.findAll()) {
            if (c.identity().getStudyPlanID().equals(studyPlanID)) {
                result.add(assembler.toServiceDTO(c));
            }
        }
        return result;
    }

    @Override
    public List<CourseInStudyPlan> getCoursesByProgrammeID(ProgrammeID programmeID) {
        List<CourseInStudyPlan> result = new ArrayList<>();
        for (CourseInStudyPlan c : repository.findAll()) {
            if (c.getProgrammeID().equals(programmeID)) {
                result.add(c);
            }
        }
        return result;
    }
}