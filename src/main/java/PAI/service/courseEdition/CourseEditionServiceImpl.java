package PAI.service.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.repository.ICourseEditionRepository;

import java.util.List;

public class CourseEditionServiceImpl implements ICourseEditionService {

    private final ICourseEditionRepository courseEditionRepository;
    private final ICourseEditionFactory courseEditionFactory;

    public CourseEditionServiceImpl(ICourseEditionFactory courseEditionFactory, ICourseEditionRepository courseEditionRepository) {

        if (courseEditionFactory == null)
            throw new IllegalArgumentException("courseEditionFactory cannot be null");
        if (courseEditionRepository == null)
            throw new IllegalArgumentException("courseEditionRepository cannot be null");

        this.courseEditionFactory = courseEditionFactory;
        this.courseEditionRepository = courseEditionRepository;
    }

    public CourseEdition createAndSaveCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        if (courseInStudyPlanID == null || programmeEditionID == null)
            return null;

        try {
            CourseEdition courseEdition = courseEditionFactory.newCourseEdition_2(courseInStudyPlanID, programmeEditionID);
            return courseEditionRepository.save(courseEdition);
        } catch (Exception e) {
            return null;
        }
    }

    public Iterable<CourseEdition> findAll() {
        return courseEditionRepository.findAll();
    }

    public List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId) {
        return null;
    }

    public CourseEdition ofIdentity(CourseEditionID courseEditionID) {
        return null;
    }

    public boolean containsOfIdentity(CourseEditionID courseEditionID) {
        return false;
    }
}
