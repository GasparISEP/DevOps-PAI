package PAI.domain.course;

import PAI.VOs.*;
import PAI.exception.BusinessRuleViolationException;
import PAI.repository.courseRepository.ICourseRepository;
import org.springframework.stereotype.Component;

@Component
public class CourseFactoryImpl implements ICourseFactory {

    @Override
    public Course createCourse(Name name, Acronym acronym) {
        if (name == null || acronym == null) {
            throw new IllegalArgumentException ("Name and Acronym must not be null");
        }

        CourseID courseID = new CourseID(acronym, name);
        return new Course(courseID, name, acronym);
    }


    //This method is used to create a course with an existing CourseID
    //in cases where the CourseID is already known (e.g., when loading from a database)
    //and for testing purposes.
    @Override
    public Course createCourse(CourseID courseID, Name name, Acronym acronym) {
        if (courseID == null || name == null || acronym == null) {
            throw new IllegalArgumentException("CourseID, Name, and Acronym must not be null");
        }
        return new Course(courseID, name, acronym);
    }
}
