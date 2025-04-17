package PAI.repository.courseRepository;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.ICourseFactory;

import java.util.List;
import java.util.Optional;

public class CourseRepositoryImpl implements ICourseRepository {

    private final ICourseFactory _courseFactory;
    private List<Course> courseList;

    public CourseRepositoryImpl(ICourseFactory iCourseFactory, ICourseRepositoryListFactory iCourseRepositoryListFactory) {
        if(iCourseFactory == null){
            throw new IllegalArgumentException("iCourseFactoryDDD cannot be null");
        }
        if(iCourseRepositoryListFactory == null){
            throw new IllegalArgumentException("iCourseRepositoryListFactoryDDD cannot be null");
        }
        _courseFactory = iCourseFactory;
        courseList = iCourseRepositoryListFactory.createCourseRepositoryList();
    }

    public boolean registerCourse (Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInCurricularYear) throws Exception {

        Course course = _courseFactory.createCourse(name, acronym, quantityCreditsEcts, durationCourseInCurricularYear);

        course = save(course);
        if(course == null)
            return false;
        return true;
    }

    @Override
    public Course save(Course entity) {
        if(entity == null){
            throw new IllegalArgumentException("entity cannot be null");
        }
        if (containsOfIdentity(entity.identity())) {
            return null;
        }
        courseList.add(entity);
        return entity;
    }

    @Override
    public Iterable<Course> findAll() {
        return courseList;
    }

    @Override
    public Optional<Course> ofIdentity(CourseID id) {
        if(id == null) {
            return Optional.empty();
        }
        return courseList.stream()
                .filter(course -> course.identity().equals(id))
                .findFirst();
    }

    @Override
    public boolean containsOfIdentity(CourseID id) {
        return ofIdentity(id).isPresent();
    }
}
