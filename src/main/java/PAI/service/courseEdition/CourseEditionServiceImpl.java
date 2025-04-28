package PAI.service.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.repository.ICourseEditionRepository;

import java.util.List;
import java.util.Optional;

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
            CourseEdition courseEdition = courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID);
            return courseEditionRepository.save(courseEdition);
        } catch (Exception e) {
            return null;
        }
    }

    public Iterable<CourseEdition> findAll() {
        return courseEditionRepository.findAll();
    }

    public List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId) {
        if (programmeEditionId == null)
            return List.of();

        try {
            return courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionId);
        } catch (Exception e) {
            return List.of();
        }
    }

    public Optional<CourseEdition> ofIdentity(CourseEditionID courseEditionID) {
        if (courseEditionID == null)
            return Optional.empty();

        return courseEditionRepository.ofIdentity(courseEditionID);
    }

    public boolean containsOfIdentity(CourseEditionID courseEditionID) {
        if (courseEditionID == null)
            return false;

        return courseEditionRepository.containsOfIdentity(courseEditionID);
    }
}
