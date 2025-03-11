package PAI.repository;

import PAI.domain.Course;
import PAI.factory.CourseFactoryImpl;
import PAI.factory.CourseListFactory;

import java.util.List;

public class CourseRepository {

    private final CourseFactoryImpl _courseFactoryImpl;
    private final List<Course> _courseRepository;

//Constructor delegation calls automatic the courseFactory
   // public CourseRepository () { this(new CourseFactoryImpl()); }

    public CourseRepository (CourseFactoryImpl courseFactoryImpl, CourseListFactory courseListFactory){

        this._courseFactoryImpl = courseFactoryImpl;
       _courseRepository = courseListFactory.createCourseList();
    }

    public boolean registerCourse (String courseName, String acronym, double quantityCreditsECTS, int durationCourseInSemester) throws Exception {

        Course course = _courseFactoryImpl.createCourse(courseName, acronym, quantityCreditsECTS, durationCourseInSemester);

        if (isCourseRegistered(course))
            return false;

        _courseRepository.add(course);
        return true;
    }

    public boolean isCourseRegistered (Course course) {

        return _courseRepository.contains(course);
    }

    public List<Course> getAllCourses() {
        return _courseRepository;
    }
}
