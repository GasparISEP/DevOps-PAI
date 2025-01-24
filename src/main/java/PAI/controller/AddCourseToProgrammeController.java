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



    public boolean addCourseToProgramme(int semester, Course course) {
        try {
            programme.addCourseToASemesterOfAProgramme(semester, course);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
