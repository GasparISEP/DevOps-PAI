package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CourseInStudyPlanServiceImpl implements ICourseInStudyPlanService {

        private final ICourseInStudyPlanRepository _repository;
        private final ICourseInStudyPlanFactory _factory;

        public CourseInStudyPlanServiceImpl (ICourseInStudyPlanRepository repository, ICourseInStudyPlanFactory factory) {
            this._repository = repository;
            this._factory = factory;
        }

        public boolean createCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                               DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) throws Exception {
            CourseInStudyPlan candidate = _factory.newCourseInStudyPlan(
                    semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts);
            CourseInStudyPlanID id = candidate.identity();

            if (_repository.containsOfIdentity(id)) {
                return false;
            }

            _repository.save(candidate);
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
}
