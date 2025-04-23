package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import PAI.domain.Teacher;

public interface ICourseEditionApplicationService {

    /**
     * Assigns a Responsible Unit Coordinator (RUC) to a course edition.
     *
     * @param teacherID        ID of the teacher to assign as RUC.
     * @param courseEditionID  ID of the course edition to update.
     * @return true if the RUC was successfully assigned, false otherwise.
     */
    boolean assignRucToCourseEdition(TeacherID teacherID, CourseEditionID courseEditionID) throws Exception;

    /**
     * Retrieves all registered teachers.
     *
     * @return Iterable list of teachers.
     */
    Iterable<Teacher> getAllTeachers();

    /**
     * Retrieves all course editions.
     *
     * @return Iterable list of course editions.
     */
    Iterable<CourseEdition> getAllCourseEditions();
}
