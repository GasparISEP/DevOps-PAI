package PAI.controller;

import PAI.VOs.*;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class US16_EnrolAStudentInACourseEditionController {

    private final ICourseEditionEnrolmentService _courseEditionEnrolmentServiceInterface;

    public US16_EnrolAStudentInACourseEditionController(
            ICourseEditionEnrolmentService courseEditionEnrolmentServiceInterface) {

        validateCourseEditionEnrolmentServiceInterface (courseEditionEnrolmentServiceInterface);

        this._courseEditionEnrolmentServiceInterface = courseEditionEnrolmentServiceInterface;
    }

    //show a list of programme editions that student is enrolled
    public List<ProgrammeEditionID> findProgrammeEditionIDsThatStudentIsEnrolled(StudentID studentId) {

        return _courseEditionEnrolmentServiceInterface.findProgrammeEditionIDsThatStudentIsEnrolled (studentId);
    }

    //show a list of course editions that belongs to a course edition for student choose a course edition
    public List<CourseEditionID> findCourseEditionIDsByProgrammeEdition(ProgrammeEditionID programmeEditionID) {

        return _courseEditionEnrolmentServiceInterface.findCourseEditionIDsByProgrammeEdition(programmeEditionID);
    }

    //enrol a student in a course edition
    public boolean enrolStudentInCourseEdition(CourseEditionEnrolmentGeneratedID uuid, StudentID studentId, CourseEditionID courseEditionId, Date enrolmentDate, EnrolmentStatus status) {

        return _courseEditionEnrolmentServiceInterface.enrolStudentInACourseEditionFromPersistence(uuid, studentId, courseEditionId, enrolmentDate, status);
    }

    //verify if the course edition enrollment service interface is valid
    private void validateCourseEditionEnrolmentServiceInterface (ICourseEditionEnrolmentService ceeServiceInterface) throws IllegalArgumentException {
        if (ceeServiceInterface == null) {
            throw new IllegalArgumentException("Course edition enrolment service interface cannot be null!");
        }
    }
}
