package PAI.persistence.springdata.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.mapper.courseEdition.ICourseEditionMapper;
import PAI.repository.ICourseEditionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseEditionRepositorySpringDataImpl implements ICourseEditionRepository {

    private final ICourseEditionRepositorySpringData iCourseEditionRepositorySpringData;
    private final ICourseEditionMapper iCourseEditionMapper;

    public CourseEditionRepositorySpringDataImpl(ICourseEditionRepositorySpringData CourseEditionReposSD, ICourseEditionMapper CourseEditionMapper) {

        if (CourseEditionReposSD == null)
            throw new IllegalArgumentException("CourseEditionRepositorySpringData cannot be null");
        if (CourseEditionMapper == null)
            throw new IllegalArgumentException("CourseEditionMapper cannot be null");

        this.iCourseEditionRepositorySpringData = CourseEditionReposSD;
        this.iCourseEditionMapper = CourseEditionMapper;
    }

    @Override
    public boolean createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        return false;
    }

    @Override
    public List<CourseEditionID> findCourseEditionsByProgrammeEdition(ProgrammeEditionID programmeEditionId) {
        return null;
    }

    @Override
    public Optional<CourseEditionID> findIdByCourseEdition(CourseEdition courseEdition2) {
        return Optional.empty();
    }

    @Override
    public ProgrammeEditionID findWhichProgrammeEditionBelongsToACourseEdition(CourseEdition courseEdition) throws Exception {
        return null;
    }

    @Override
    public CourseEdition save(CourseEdition entity) {
        return null;
    }

    @Override
    public Iterable<CourseEdition> findAll() {
        return null;
    }

    @Override
    public Optional<CourseEdition> ofIdentity(CourseEditionID id) {
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(CourseEditionID id) {
        return false;
    }
}
