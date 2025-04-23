package PAI.domain.course;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class CourseFactoryImpl implements ICourseFactory {

    public Course createCourse(Name name, Acronym acronym) {
        CourseID courseID = new CourseID(acronym, name);
        return new Course(courseID, name, acronym);
    }


    public Course createCourse(CourseID courseID, Name name, Acronym acronym) {
        return new Course(courseID, name, acronym);
    }
}
