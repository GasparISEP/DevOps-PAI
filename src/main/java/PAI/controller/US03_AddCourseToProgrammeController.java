package PAI.controller;

import PAI.domain.Course;
import PAI.domain.Programme;

public class US03_AddCourseToProgrammeController {

    private final Programme programme;
    
    public US03_AddCourseToProgrammeController(Programme programme) throws Exception {
        if(programme == null) {
            throw new IllegalArgumentException("Programme cannot be null.");
        }
        this.programme = programme;
    }



    public boolean addCourseToProgramme(Course course) throws Exception {
        if(course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        programme.addCourseToAProgramme(course);
        return true;
    }
    
}
