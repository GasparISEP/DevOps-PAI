package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.repository.*;

import java.util.Collections;
import java.util.List;


public class US16_EnrolAStudentInACourseEditionController {

    private final ICourseEditionEnrolmentRepository _ceeRepositoryInterface;
    private final IProgrammeEditionEnrolmentRepository _peeRepositoryInterface;
    private final ICourseEditionRepository _courseEditionRepositoryInterface;


    public US16_EnrolAStudentInACourseEditionController(
            ICourseEditionEnrolmentRepository _ceeRepositoryInterface, IProgrammeEditionEnrolmentRepository peeRepositoryInterface, ICourseEditionRepository courseEditionRepositoryInterface) {

        validateCourseEditionEnrolmentRepository (_ceeRepositoryInterface);
        validateProgrammeEditionEnrolmentRepository (peeRepositoryInterface);
        validateCourseEditionRepository (courseEditionRepositoryInterface);

        this._ceeRepositoryInterface = _ceeRepositoryInterface;
        this._peeRepositoryInterface = peeRepositoryInterface;
        this._courseEditionRepositoryInterface = courseEditionRepositoryInterface;
    }

    //show a list of programme editions that student is enrolled
    public List<ProgrammeEditionID> findProgrammeEditionIDsThatStudentIsEnrolled(StudentID studentId) {

        if (studentId == null) {
            return Collections.emptyList();
        }

        return _peeRepositoryInterface.findProgrammeEditionsThatStudentIsEnrolled (studentId);
    }

    //show a list of course editions that belongs to a course edition for student choose a course edition
    public List<CourseEditionID> findCourseEditionIDsByProgrammeEdition(ProgrammeEditionID programmeEditionID) {
        return _courseEditionRepositoryInterface.findCourseEditionsByProgrammeEditionID(programmeEditionID);
    }

    //enrol a student in a course edition
    public boolean enrolStudentInCourseEdition(StudentID studentId, CourseEditionID courseEditionId) throws Exception {
        return _ceeRepositoryInterface.enrolStudentInACourseEdition(studentId, courseEditionId);
    }

    //Verify if the course edition enrollment repository is valid
    private void validateCourseEditionEnrolmentRepository (ICourseEditionEnrolmentRepository ceeRepositoryInterface) throws IllegalArgumentException {
        if (ceeRepositoryInterface == null) {
            throw new IllegalArgumentException("Course edition enrolment repository interface cannot be null!");
        }
    }

    //Verify if the programme edition enrollment repository is valid
    private void validateProgrammeEditionEnrolmentRepository (IProgrammeEditionEnrolmentRepository peeRepositoryInterface) throws IllegalArgumentException {
        if (peeRepositoryInterface == null) {
            throw new IllegalArgumentException("Programme edition enrolment repository interface cannot be null!");
        }
    }

    //verify if the course edition repository is valid
    private void validateCourseEditionRepository (ICourseEditionRepository courseEditionRepositoryInterface) throws IllegalArgumentException {
        if (courseEditionRepositoryInterface == null) {
            throw new IllegalArgumentException("Course edition repository cannot be null!");
        }
    }
}
