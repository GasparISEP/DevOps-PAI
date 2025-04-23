package PAI.persistence.springdata.course;

import PAI.VOs.CourseID;
import PAI.domain.course.Course;
import PAI.mapper.Course.ICourseMapper;
import PAI.mapper.CourseID.ICourseIDMapper;
import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.repository.courseRepository.ICourseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CourseRepositorySpringDataImpl implements ICourseRepository {

    private final ICourseRepositorySpringData _iCourseRepo;
    private final ICourseMapper _iCourseMapper;
    private final ICourseIDMapper _iCourseIDMapper;

    public CourseRepositorySpringDataImpl(ICourseRepositorySpringData iCourseRepo, ICourseMapper iCourseMapper, ICourseIDMapper iCourseIDMapper) {

        if(iCourseRepo == null) {
            throw new IllegalArgumentException("iCourseRepositorySpringData must not be null");
        }
        if(iCourseMapper == null) {
            throw new IllegalArgumentException("iCourseMapper must not be null");
        }
        if(iCourseIDMapper == null) {
            throw new IllegalArgumentException("iCourseIDMapper must not be null");
        }

        _iCourseRepo = iCourseRepo;
        _iCourseMapper = iCourseMapper;
        _iCourseIDMapper = iCourseIDMapper;
    }

    @Override
    public Course save(Course entity) {
        if(entity == null) {
            return null;
        }
        try {
        CourseDataModel courseDataModel = _iCourseMapper.toDataModel(entity);
        if (!containsOfIdentity(entity.identity())) {
            _iCourseRepo.save(courseDataModel);
        }
        else {
            return null;
        }
            return _iCourseMapper.toDomain(courseDataModel);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Iterable<Course> findAll() {
        Iterable<CourseDataModel> courseDataModels = _iCourseRepo.findAll();
        Iterable<Course> courses = _iCourseMapper.toDomain(courseDataModels);

        return courses;
    }

    @Override
    public Optional<Course> ofIdentity(CourseID id) {
        Optional<CourseDataModel> optCourseDataModelSaved = _iCourseRepo.findById(_iCourseIDMapper.toDataModel(id));
        if (optCourseDataModelSaved.isEmpty())
            return Optional.empty();
        try {
            Course courseDomain = _iCourseMapper.toDomain(optCourseDataModelSaved.get());
            return Optional.of(courseDomain);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean containsOfIdentity(CourseID id) {
        if (id == null) {
            return false;
        }
        return _iCourseRepo.existsById(_iCourseIDMapper.toDataModel(id));
    }
}
