package PAI.controller;

import PAI.domain.Course;
import PAI.domain.Programme;

public class US03_AddCourseToProgrammeController {

    private final Programme programme;
    
    public US03_AddCourseToProgrammeController(Programme programme) {
        this.programme = programme;
    }



    public boolean addCourseToProgramme(Course course) throws Exception {
        programme.addCourseToAProgramme(course);
        return true;
    }
    
}
