package PAI.service.courseEdition;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.teacher.Teacher;

public interface IDefineRucService {
    Iterable<CourseEdition> findAll();
    Iterable <Teacher> findAllTeachers();
    boolean assignRucToCourseEdition(TeacherID teacherID, CourseEditionID courseEditionID);
    boolean containsOfIdentity(CourseEditionID courseEditionID);
}
