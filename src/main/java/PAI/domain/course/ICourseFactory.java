package PAI.domain.course;

import PAI.VOs.*;

public interface ICourseFactory {

    Course createCourse(Name name, Acronym acronym);


    //This method is used to create a course with an existing CourseID
    //in cases where the CourseID is already known (e.g., when loading from a database)
    //and for testing purposes.
    Course createCourse(CourseID courseID, Name name, Acronym acronym);
}
