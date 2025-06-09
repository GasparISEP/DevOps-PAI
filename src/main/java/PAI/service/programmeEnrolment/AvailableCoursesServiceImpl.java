package PAI.service.programmeEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


}
