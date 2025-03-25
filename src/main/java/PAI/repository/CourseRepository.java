package PAI.repository;

import PAI.domain.Course;
import PAI.factory.ICourseFactory;
import PAI.factory.CourseListFactoryImpl;

import java.util.List;

public class CourseRepository {

    private final ICourseFactory _I_courseFactory;
    private final List<Course> _courseRepository;

//Constructor delegation calls automatic the courseFactory
   // public CourseRepository () { this(new CourseFactory()); }

    public CourseRepository (ICourseFactory ICourseFactory, CourseListFactoryImpl courseListFactoryImpl){

        this._I_courseFactory = ICourseFactory;
       _courseRepository = courseListFactoryImpl.createCourseList();
    }

    public boolean registerCourse (String courseName, String acronym, double quantityCreditsECTS, int durationCourseInSemester) throws Exception {

        Course course = _I_courseFactory.createCourse(courseName, acronym, quantityCreditsECTS, durationCourseInSemester);

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
