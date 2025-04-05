package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.factory.ICourseEditionListFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseEditionRepositoryImpl implements ICourseEditionRepository {

    private final List<CourseEdition> _courseEditions;
    private final ICourseEditionFactory _courseEditionFactory;

    public CourseEditionRepositoryImpl(ICourseEditionFactory courseEditionFactory, ICourseEditionListFactory courseEditionListFactory) {

        _courseEditionFactory = courseEditionFactory;
        _courseEditions = courseEditionListFactory.newList();

    }

    public boolean createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        try {
            CourseEdition courseEdition = _courseEditionFactory.newCourseEdition_2(courseInStudyPlanID, programmeEditionID);
           if (containsOfIdentity(courseEdition.identity()))
                return false;

            _courseEditions.add(courseEdition);
            return true;

        } catch (Exception exception) {
            return false;
        }
    }


    @Override
    public CourseEdition save(CourseEdition courseEdition) {
        if (courseEdition == null){
            throw new IllegalArgumentException("Course edition cannot be null");
        }
        if (courseEdition.identity() == null){
            throw new IllegalArgumentException("Course edition ID cannot be null");
        }
        _courseEditions.add(courseEdition);

        return courseEdition;
    }

    @Override
    public Iterable<CourseEdition> findAll() {
        return _courseEditions;
    }

    @Override
    public Optional<CourseEdition> ofIdentity(CourseEditionID courseEditionID) {
        if (courseEditionID == null){
            throw new IllegalArgumentException("Course edition ID cannot be null");
        }
        if (!containsOfIdentity(courseEditionID)){
            return Optional.empty();
        }
        return _courseEditions.stream()
                .filter(courseEdition -> courseEdition.identity().equals(courseEditionID))
                .findAny();
    }

    @Override
    public boolean containsOfIdentity(CourseEditionID courseEditionID) {
            for (CourseEdition courseEdition : _courseEditions) {
                if (courseEdition.identity().equals(courseEditionID))
                    return true;
            }
        return false;
    }

    public List<CourseEditionID> findCourseEditionsByProgrammeEdition(ProgrammeEditionID programmeEditionId) {
        List<CourseEditionID> result = new ArrayList<>();
        for (CourseEdition courseEdition : _courseEditions) {
            if (courseEdition.getProgrammeEditionID().equals(programmeEditionId)) {
                result.add(courseEdition.identity());
            }
        }

        return result;
    }
    public Optional<CourseEditionID> findIdByCourseEdition (CourseEdition courseEdition2){
        for (CourseEdition existingCourseEdition_DDD : _courseEditions){
            if (existingCourseEdition_DDD.equals(courseEdition2)){
                return Optional.of(existingCourseEdition_DDD.identity());
            }
        }
        return Optional.empty();
    }

    public ProgrammeEditionID findWhichProgrammeEditionBelongsToACourseEdition(CourseEdition courseEdition) throws Exception {
        for (CourseEdition courseEdition1 : _courseEditions)
            if (courseEdition1.equals(courseEdition)) {
                return courseEdition1.getProgrammeEditionID();
            }

        throw new Exception("The course edition does not belong to the course Edition Repository.");
    }
}
