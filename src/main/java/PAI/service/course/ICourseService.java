package PAI.service.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.domain.course.Course;

import java.util.Optional;

public interface ICourseService {

    public Course newCourse (Name name, Acronym acronym);

    public Iterable <Course> findAll();

    public Optional<Course> ofIdentity(CourseID courseID);

    public boolean containsOfIdentity(CourseID courseID);

}
