package PAI.controller;

import PAI.domain.Course;
import PAI.domain.Programme;

public class AddCourseToProgrammeController {   

    private Programme programme;
    
    public AddCourseToProgrammeController(Programme programme) {
        if (programme == null) {
            throw new IllegalArgumentException("Programme cannot be null.");
        }
        this.programme = programme;
    }



    public boolean addCourseToProgramme(int semester, Course course) throws Exception {
        programme.addCourseToASemesterOfAProgramme(semester, course);
        return true;
    }
    
}
