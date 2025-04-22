package PAI.persistence.springdata.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.mapper.courseEdition.ICourseEditionMapper;
import PAI.repository.ICourseEditionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseEditionRepositorySpringDataImpl implements ICourseEditionRepository {

    private final ICourseEditionRepositorySpringData courseEditionRepositorySpringData;
    private final ICourseEditionMapper courseEditionMapper;
    private final ICourseEditionIDMapper courseEditionIDMapper;

    public CourseEditionRepositorySpringDataImpl(ICourseEditionRepositorySpringData courseEditionReposSD, ICourseEditionMapper courseEditionMapper, ICourseEditionIDMapper courseEditionIDMapper) {

        if (courseEditionReposSD == null)
            throw new IllegalArgumentException("CourseEditionRepositorySpringData cannot be null");
        if (courseEditionMapper == null)
            throw new IllegalArgumentException("CourseEditionMapper cannot be null");
        if (courseEditionIDMapper == null)
            throw new IllegalArgumentException("CourseEditionIDMapper cannot be null");

        this.courseEditionRepositorySpringData = courseEditionReposSD;
        this.courseEditionMapper = courseEditionMapper;
        this.courseEditionIDMapper = courseEditionIDMapper;
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
        if (id == null)
            return false;


        return false;
    }
}
