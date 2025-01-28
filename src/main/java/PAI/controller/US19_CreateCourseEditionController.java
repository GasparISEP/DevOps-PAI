package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.domain.Course;
import PAI.domain.ProgrammeEdition;

import java.util.ArrayList;
import java.util.List;

public class US19_CreateCourseEditionController {

    private List<CourseEdition> courseEditions = new ArrayList<>();

    public CourseEdition createCourseEdition(Course course, ProgrammeEdition programmeEdition) throws Exception {
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);
        courseEditions.add(courseEdition);
        return courseEdition;
    }

    public List<CourseEdition> getAllCourseEditions() {
        return courseEditions;
    }
}