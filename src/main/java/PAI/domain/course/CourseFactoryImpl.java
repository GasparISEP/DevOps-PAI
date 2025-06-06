package PAI.domain.course;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class CourseFactoryImpl implements ICourseFactory {

    @Override
    public Course createCourse(Name name, Acronym acronym) {
        if (name == null || acronym == null) {
            throw new IllegalArgumentException ("Name and Acronym must not be null");
        }
        CourseGeneratedID courseGeneratedID = new CourseGeneratedID();
        CourseID courseID = new CourseID(acronym, name);
        return new Course(courseGeneratedID, courseID, name, acronym);
    }


    //This method is used to create a course with an existing CourseID and CourseGeneratedID
    //in cases where the CourseID is already known (e.g., when loading from a database)
    //and for testing purposes.
    @Override
    public Course createCourse(CourseGeneratedID courseGeneratedID, CourseID courseID, Name name, Acronym acronym) {
        if (courseID == null || name == null || acronym == null) {
            throw new IllegalArgumentException("CourseID, Name, and Acronym must not be null");
        }
        return new Course(courseGeneratedID, courseID, name, acronym);
    }
}
