package PAI.repository;

import PAI.domain.Course;
import PAI.factory.CourseFactory;
import PAI.factory.CourseListFactory;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    private final CourseFactory _courseFactory;
    private final List<Course> _courseRepository;

//Constructor delegation calls automatic the courseFactory
   // public CourseRepository () { this(new CourseFactory()); }

    public CourseRepository (CourseFactory courseFactory, CourseListFactory courseListFactory){

        this._courseFactory = courseFactory;
       _courseRepository = courseListFactory.createCourseList();
    }

    public boolean registerCourse (String courseName, String acronym, double quantityCreditsECTS, int durationCourseInSemester) throws Exception {

        Course course = _courseFactory.createCourse(courseName, acronym, quantityCreditsECTS, durationCourseInSemester);

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
