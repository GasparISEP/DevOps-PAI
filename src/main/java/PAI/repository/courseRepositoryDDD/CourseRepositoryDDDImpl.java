package PAI.repository.courseRepositoryDDD;

import PAI.VOs.CourseID;
import PAI.domain.CourseDDD;

import java.util.List;
import java.util.Optional;

public class CourseRepositoryDDDImpl implements ICourseRepositoryDDD {

    private List<CourseDDD> courseList;

    public CourseRepositoryDDDImpl(ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD) {
        if(iCourseRepositoryListFactoryDDD == null){
            throw new IllegalArgumentException("iCourseRepositoryListFactoryDDD cannot be null");
        }
        courseList = iCourseRepositoryListFactoryDDD.createCourseRepositoryList();
    }

    @Override
    public CourseDDD save(CourseDDD entity) {
        if(entity == null){
            throw new IllegalArgumentException("entity cannot be null");
        }
        courseList.add(entity);
        return entity;
    }

    @Override
    public Iterable<CourseDDD> findAll() {
        return courseList;
    }

    @Override
    public Optional<CourseDDD> ofIdentity(CourseID id) {
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
