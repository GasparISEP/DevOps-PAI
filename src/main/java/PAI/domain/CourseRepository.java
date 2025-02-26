package PAI.domain;

import java.util.ArrayList;

public class CourseRepository {

    private ArrayList<Course> _courseRepository;

    private CourseFactory _courseFactory;


//Constructor delegation calls automatic the courseFactory
    public CourseRepository () { this(new CourseFactory()); }

    public CourseRepository (CourseFactory courseFactory){

        this._courseFactory = courseFactory;

        _courseRepository = new ArrayList<>();
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

    public ArrayList<Course> getAllCourses() {
        return _courseRepository;
    }
}
