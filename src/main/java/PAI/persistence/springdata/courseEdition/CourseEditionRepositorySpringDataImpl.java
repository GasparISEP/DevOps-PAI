package PAI.persistence.springdata.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.domain.course.Course;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.mapper.courseEdition.ICourseEditionMapper;
import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.repository.ICourseEditionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

        Iterable<CourseEditionDataModel> courseEditionDataModels = courseEditionRepositorySpringData.findAll();
        List<CourseEdition> courseEditions = new ArrayList<>();
        for (CourseEditionDataModel courseEditionDataModel : courseEditionDataModels) {
            try {
                CourseEdition courseEdition = courseEditionMapper.toDomain(courseEditionDataModel);
                if (courseEdition != null)
                    courseEditions.add(courseEdition);
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        return courseEditions;
    }

    @Override
    public Optional<CourseEdition> ofIdentity(CourseEditionID id) {
        if (id == null)
            return Optional.empty();
        try {
            CourseEditionIDDataModel courseEditionIDDataModel = courseEditionIDMapper.toDataModel(id);
            Optional<CourseEditionDataModel> courseEditionDataModelOptional = courseEditionRepositorySpringData.findById(courseEditionIDDataModel);
            if (courseEditionDataModelOptional.isPresent())
                return Optional.of(courseEditionMapper.toDomain(courseEditionDataModelOptional.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(CourseEditionID id) {
        if (id == null)
            return false;
        try {
            return courseEditionRepositorySpringData.existsById(courseEditionIDMapper.toDataModel(id));
        } catch (Exception e) {
            return false;
        }
    }
}
