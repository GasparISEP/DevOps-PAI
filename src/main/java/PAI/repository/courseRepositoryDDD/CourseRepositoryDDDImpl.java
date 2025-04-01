package PAI.repository.courseRepositoryDDD;

import PAI.VOs.*;
import PAI.domain.Course;
import PAI.domain.course.CourseDDD;
import PAI.domain.course.ICourseFactoryDDD;

import java.util.List;
import java.util.Optional;

public class CourseRepositoryDDDImpl implements ICourseRepositoryDDD {

    private final ICourseFactoryDDD _courseFactory;
    private List<CourseDDD> courseList;

    public CourseRepositoryDDDImpl(ICourseFactoryDDD iCourseFactoryDDD,ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD) {
        if(iCourseFactoryDDD == null){
            throw new IllegalArgumentException("iCourseFactoryDDD cannot be null");
        }
        if(iCourseRepositoryListFactoryDDD == null){
            throw new IllegalArgumentException("iCourseRepositoryListFactoryDDD cannot be null");
        }
        _courseFactory = iCourseFactoryDDD;
        courseList = iCourseRepositoryListFactoryDDD.createCourseRepositoryList();
    }

    public boolean registerCourse (CourseID id, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInCurricularYear) throws Exception {

        CourseDDD courseDDD = _courseFactory.createCourse(id, name, acronym, quantityCreditsEcts, durationCourseInCurricularYear);

//        if(containsOfIdentity(courseDDD.identity()))
//            return false;

        courseDDD = save(courseDDD);
        if(courseDDD == null)
            return false;
        return true;

//        if (containsOfIdentity(id)) {
//            return false;
//        }
//        CourseDDD course = _courseFactory.createCourse(id, name, acronym, quantityCreditsEcts, durationCourseInCurricularYear);
//        save(course);
//        return true;
    }

    @Override
    public CourseDDD save(CourseDDD entity) {
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
