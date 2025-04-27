package PAI.persistence.mem.courseRepository;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.ICourseFactory;
import PAI.repository.courseRepository.ICourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseRepositoryImpl implements ICourseRepository {

    private List<Course> courseList;

    public CourseRepositoryImpl(ICourseRepositoryListFactory iCourseRepositoryListFactory) {

        if(iCourseRepositoryListFactory == null){
            throw new IllegalArgumentException("iCourseRepositoryListFactoryDDD cannot be null");
        }
        courseList = iCourseRepositoryListFactory.createCourseRepositoryList();
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

    @Override
    public boolean existsCourseByAcronym(Acronym courseAcronym) {
        if(courseAcronym == null) {
            return false;
        }
        for (Course course : courseList) {
            if (course.identity().getAcronym().equals(courseAcronym)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsCourseByName(Name courseName) {
        if(courseName == null) {
            return false;
        }
        for (Course course : courseList) {
            if (course.identity().getName().equals(courseName)) {
                return true;
            }
        }
        return false;
    }
}
