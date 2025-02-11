package PAI.controller;

import PAI.domain.Course;
import PAI.domain.CourseRepository;
import PAI.domain.Programme;
import PAI.domain.ProgrammeList;

import java.util.List;

public class US03_AddCourseToProgrammeController {

    private final ProgrammeList programmeList;
    private final CourseRepository courseRepository;
    
    public US03_AddCourseToProgrammeController(ProgrammeList programmeList, CourseRepository courseRepository) throws Exception {
        if(programmeList == null) {
            throw new IllegalArgumentException("ProgrammeList cannot be null.");
        }
        if (courseRepository == null) {
            throw new IllegalArgumentException("CourseRepository cannot be null.");
        }
        this.programmeList = programmeList;
        this.courseRepository = courseRepository;
    }

    public List<Programme> getAllProgrammes() {
        return programmeList.getAllProgrammes();
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public boolean addCourseToProgramme(Programme programme, Course course) throws Exception {
        if(course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        programme.addCourseToAProgramme(course);
        return true;
    }
    
}
