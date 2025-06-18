package PAI.service.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseEdition.ICourseEditionFactory;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.exception.NotFoundException;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.stereotype.Service;

import static org.junit.Assert.assertThrows;

@Service
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
            CourseEdition courseEditionSaved = null;
            if (!courseEditionRepository.containsOfIdentity(courseEdition.identity()))
                courseEditionSaved = courseEditionRepository.save(courseEdition);
            return courseEditionSaved;
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

    @Override
    public List<CourseEditionID> findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(ProgrammeEditionID programmeEditionID, CourseInStudyPlanID courseInStudyPlanID) throws Exception {
        if(programmeEditionID == null) {
            throw new IllegalArgumentException("ProgrammeEditionID cannot be null");
        }
        if(courseInStudyPlanID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID cannot be null");
        }
        return courseEditionRepository.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID);
    }

    @Override
    public CourseEditionID findCourseEditionByGeneratedID (CourseEditionGeneratedID generatedID) throws Exception {

        if (generatedID == null) {
            throw new IllegalArgumentException("Course Edition Generated ID cannot be null.");
        }

        Optional<CourseEdition> courseEdition = courseEditionRepository.findCourseEditionByGeneratedId(generatedID);

        if (courseEdition.isPresent()) {
            return courseEdition.get().identity();
        } else {
            throw new NotFoundException("CourseEdition not found with Universally Unique ID: " + generatedID);
        }
    }

    @Override
    public List<CourseEdition> getCourseEditionsByProgrammeIDAndCourseID(ProgrammeID programmeID, CourseID courseID) {
        return courseEditionRepository.findByProgrammeIDAndCourseID(programmeID, courseID);
    }

    @Override
    public List<SchoolYearID> getSchoolYearIDsFromCourseEditions(Iterable<CourseEdition> courseEditions) {
        List<SchoolYearID> schoolYearIDs = new ArrayList<>();
        for (CourseEdition ce : courseEditions) {
            schoolYearIDs.add(ce.getProgrammeEditionID().getSchoolYearID());
        }
        return schoolYearIDs;
    }
}