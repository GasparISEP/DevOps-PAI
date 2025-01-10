package PAI.domain;

import java.util.ArrayList;

public class CourseRepository {

    private ArrayList<Course> _courseRepository;

    public CourseRepository (){

        _courseRepository = new ArrayList<>();
    }

    public boolean registerCourse (String courseName, String acronym, int quantityCreditsECTS) throws Exception {

        Course course = new Course (courseName, acronym, quantityCreditsECTS);

        if (isCourseRegistered(course))
            return false;

        _courseRepository.add(course);
        return true;
    }

    public boolean isCourseRegistered (Course course) {

        return _courseRepository.contains(course);
    }
}
