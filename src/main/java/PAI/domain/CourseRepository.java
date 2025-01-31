package PAI.domain;

import java.util.ArrayList;

public class CourseRepository {

    private ArrayList<Course> _courseRepository;

    public CourseRepository (){

        _courseRepository = new ArrayList<>();
    }

    public boolean registerCourse (String courseName, String acronym, double quantityCreditsECTS, int durationCourseInSemester) throws Exception {

        Course course = new Course (courseName, acronym, quantityCreditsECTS, durationCourseInSemester);

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
