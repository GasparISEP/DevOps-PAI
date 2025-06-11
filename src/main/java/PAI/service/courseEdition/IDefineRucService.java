package PAI.service.courseEdition;
import PAI.VOs.CourseEditionGeneratedID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.teacher.Teacher;

public interface IDefineRucService {
    Iterable<CourseEdition> findAll();
    Iterable <Teacher> findAllTeachers();
    boolean assignRucToCourseEdition(TeacherID teacherID, CourseEditionGeneratedID courseEditionID) throws Exception;
    boolean containsOfIdentity(CourseEditionID courseEditionID);
}
