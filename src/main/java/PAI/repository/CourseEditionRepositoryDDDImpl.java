package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEditionDDD;
import PAI.factory.ICourseEditionFactoryDDD;
import PAI.factory.ICourseEditionListFactoryDDD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseEditionRepositoryDDDImpl implements ICourseEditionRepositoryDDD {

    private final List<CourseEditionDDD> _courseEditions;
    private final ICourseEditionFactoryDDD _courseEditionFactory;

    public CourseEditionRepositoryDDDImpl(ICourseEditionFactoryDDD courseEditionFactory, ICourseEditionListFactoryDDD courseEditionListFactory) {

        _courseEditionFactory = courseEditionFactory;
        _courseEditions = courseEditionListFactory.newList();

    }

    public boolean createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        try {
            CourseEditionDDD courseEdition = _courseEditionFactory.newCourseEdition_2(courseInStudyPlanID, programmeEditionID);
           if (containsOfIdentity(courseEdition.identity()))
                return false;

            _courseEditions.add(courseEdition);
            return true;

        } catch (Exception exception) {
            return false;
        }
    }


    @Override
    public CourseEditionDDD save(CourseEditionDDD courseEdition) {
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
    public Iterable<CourseEditionDDD> findAll() {
        return _courseEditions;
    }

    @Override
    public Optional<CourseEditionDDD> ofIdentity(CourseEditionID courseEditionID) {
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
            for (CourseEditionDDD courseEdition : _courseEditions) {
                if (courseEdition.identity().equals(courseEditionID))
                    return true;
            }
        return false;
    }

    public List<CourseEditionID> findCourseEditionsByProgrammeEdition(ProgrammeEditionID programmeEditionId) {
        List<CourseEditionID> result = new ArrayList<>();
        for (CourseEditionDDD courseEdition : _courseEditions) {
            if (courseEdition.getProgrammeEditionID().equals(programmeEditionId)) {
                result.add(courseEdition.identity());
            }
        }

        return result;
    }
    public Optional<CourseEditionID> findIdByCourseEdition (CourseEditionDDD courseEdition2){
        for (CourseEditionDDD existingCourseEdition_DDD : _courseEditions){
            if (existingCourseEdition_DDD.equals(courseEdition2)){
                return Optional.of(existingCourseEdition_DDD.identity());
            }
        }
        return Optional.empty();
    }
}
