package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition_2;
import PAI.domain.ProgrammeEdition;
import PAI.factory.ICourseEditionFactory_2;
import PAI.factory.ICourseEditionListFactory_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseEditionRepositoryImpl implements ICourseEditionRepository {

    private final List<CourseEdition_2> _courseEditions;
    private final ICourseEditionFactory_2 _courseEditionFactory;

    public CourseEditionRepositoryImpl(ICourseEditionFactory_2 courseEditionFactory, ICourseEditionListFactory_2 courseEditionListFactory) {

        _courseEditionFactory = courseEditionFactory;
        _courseEditions = courseEditionListFactory.newList();

    }

    public boolean createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        try {
            CourseEdition_2 courseEdition = _courseEditionFactory.newCourseEdition_2(courseInStudyPlanID, programmeEditionID);
           if (containsOfIdentity(courseEdition.identity()))
                return false;

            _courseEditions.add(courseEdition);
            return true;

        } catch (Exception exception) {
            return false;
        }
    }


    @Override
    public CourseEdition_2 save(CourseEdition_2 courseEdition) {
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
    public Iterable<CourseEdition_2> findAll() {
        return _courseEditions;
    }

    @Override
    public Optional<CourseEdition_2> ofIdentity(CourseEditionID courseEditionID) {
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
            for (CourseEdition_2 courseEdition : _courseEditions) {
                if (courseEdition.identity().equals(courseEditionID))
                    return true;
            }
        return false;
    }

    public List<CourseEditionID> findCourseEditionsByProgrammeEdition(ProgrammeEditionID programmeEditionId) {
        List<CourseEditionID> result = new ArrayList<>();
        for (CourseEdition_2 courseEdition : _courseEditions) {
            if (courseEdition.getProgrammeEditionID().equals(programmeEditionId)) {
                result.add(courseEdition.identity());
            }
        }

        return result;
    }
}
