package PAI.service.programmeEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvailableCoursesServiceImpl {
    private final ICourseEditionRepository _courseEditionRepository;
    private final ICourseInStudyPlanRepository _courseInStudyPlanRepository;

    public AvailableCoursesServiceImpl(ICourseEditionRepository courseEditionRepository, ICourseInStudyPlanRepository courseInStudyPlanRepository){
        if ( courseEditionRepository == null || courseInStudyPlanRepository == null) throw new IllegalArgumentException("Cannot be null");
        _courseEditionRepository = courseEditionRepository;
        _courseInStudyPlanRepository = courseInStudyPlanRepository;
    }

    public List<CourseEditionID> allCourseEditionIdsFromProgrammeEdition(ProgrammeEditionID programmeEditionID){
        return _courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID);
    }

    public List<CourseInStudyPlanID> allCoursesInStudyFromProgrammeEdition(List<CourseEditionID> courseEditionIDS){
        List<CourseInStudyPlanID> courseInStudyPlanIDS = new ArrayList<>();
        for (CourseEditionID existingCourseEdition : courseEditionIDS){
            courseInStudyPlanIDS.add(existingCourseEdition.getCourseInStudyPlanID());
        }
        return courseInStudyPlanIDS;
    }

    public List<CourseInStudyPlan> getByIdentity(List<CourseInStudyPlanID> courseInStudyPlanIDS) {
        List<CourseInStudyPlan> courseInStudyPlans = new ArrayList<>();
        for (CourseInStudyPlanID existingCSPID : courseInStudyPlanIDS) {
            Optional<CourseInStudyPlan> optional = _courseInStudyPlanRepository.ofIdentity(existingCSPID);
            if (optional.isPresent()) {
                courseInStudyPlans.add(optional.get());
            } else {
                throw new NotFoundException("CourseInStudyPlan not found: " + existingCSPID);
            }
        }
        return courseInStudyPlans;
    }

    public List<CourseID> getListOfCoursesID(List<CourseInStudyPlan> courseInStudyPlans){
        List<CourseID> courseIDS = new ArrayList<>();
        for (CourseInStudyPlan existingCourse : courseInStudyPlans){
            courseIDS.add(existingCourse.getCourseID());
        }
        return courseIDS;
    }
}
