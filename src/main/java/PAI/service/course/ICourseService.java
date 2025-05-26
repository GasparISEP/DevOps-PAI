package PAI.service.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.domain.course.Course;
import PAI.dto.course.CourseIDDTO;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    Course createAndSaveCourse (Name name, Acronym acronym) throws Exception;

    Iterable <Course> findAll();

    Optional<Course> ofIdentity(CourseID courseID);

    boolean containsOfIdentity(CourseID courseID);

    List<CourseIDDTO> getAllCourseIDDTO();
}
