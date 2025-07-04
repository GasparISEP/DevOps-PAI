package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvailableCoursesServiceImpl implements IAvailableCoursesService {
    private final ICourseEditionRepository _courseEditionRepository;
    private final ICourseInStudyPlanRepository _courseInStudyPlanRepository;

    public AvailableCoursesServiceImpl(ICourseEditionRepository courseEditionRepository, ICourseInStudyPlanRepository courseInStudyPlanRepository){
        if ( courseEditionRepository == null || courseInStudyPlanRepository == null) throw new IllegalArgumentException("Cannot be null");
        _courseEditionRepository = courseEditionRepository;
        _courseInStudyPlanRepository = courseInStudyPlanRepository;
    }

    @Override
    public List<CourseEditionID> allCourseEditionIdsFromProgrammeEdition(ProgrammeEditionID programmeEditionID){
        return _courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID);
    }

    @Override
    public List<CourseInStudyPlanID> allCoursesInStudyFromProgrammeEdition(List<CourseEditionID> courseEditionIDS){
        List<CourseInStudyPlanID> courseInStudyPlanIDS = new ArrayList<>();
        for (CourseEditionID existingCourseEdition : courseEditionIDS){
            courseInStudyPlanIDS.add(existingCourseEdition.getCourseInStudyPlanID());
        }
        return courseInStudyPlanIDS;
    }

    @Override
    public List<CourseInStudyPlan> getByIdentity(List<CourseInStudyPlanID> courseInStudyPlanIDS) {
        if (courseInStudyPlanIDS.size() == 0) {
            throw new NotFoundException("CourseInStudyPlan List is empty");
        }
        List<CourseInStudyPlan> courseInStudyPlans = new ArrayList<>();
        for (CourseInStudyPlanID existingCSPID : courseInStudyPlanIDS) {
            Optional<CourseInStudyPlan> optional = _courseInStudyPlanRepository.ofIdentity(existingCSPID);
            optional.ifPresent(courseInStudyPlans::add);
        }
        return courseInStudyPlans;
    }

    @Override
    public List<CourseID> getListOfCoursesID(List<CourseInStudyPlan> courseInStudyPlans){
        List<CourseID> courseIDS = new ArrayList<>();
        for (CourseInStudyPlan existingCourse : courseInStudyPlans){
            courseIDS.add(existingCourse.getCourseID());
        }
        return courseIDS;
    }

    @Override
    public List<AvailableCourseInfo> getListOfCourseInfo(List<CourseInStudyPlan> courseInStudyPlans){
        List<AvailableCourseInfo> courseInfos = new ArrayList<>();
        for (CourseInStudyPlan existingCourse : courseInStudyPlans){
            AvailableCourseInfo availableCourseInfo = new AvailableCourseInfo(existingCourse.getCourseID(),existingCourse.getQuantityOfCreditsEcts(),existingCourse.getCurricularYear(),existingCourse.getSemester());
            courseInfos.add(availableCourseInfo);
        }
        return courseInfos;
    }

    @Override
    public List<CourseID> getListOfCourseIdForAGivenProgrammeEdition(ProgrammeEditionID programmeEditionID){
        return getListOfCoursesID(getByIdentity(allCoursesInStudyFromProgrammeEdition(allCourseEditionIdsFromProgrammeEdition(programmeEditionID))));
    }

    @Override
    public List<AvailableCourseInfo> getListOfAvailableCourseInfoForAGivenProgrammeEdition(ProgrammeEditionID programmeEditionID){
        return getListOfCourseInfo(getByIdentity(allCoursesInStudyFromProgrammeEdition(allCourseEditionIdsFromProgrammeEdition(programmeEditionID))));
    }
}
