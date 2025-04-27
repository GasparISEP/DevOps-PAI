package PAI.repository.courseRepository;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.ddd.IRepository;
import PAI.domain.course.Course;

public interface ICourseRepository extends IRepository<CourseID, Course> {

    boolean existsCourseByAcronym(Acronym courseAcronym);

    boolean existsCourseByName(Name courseName);
}
