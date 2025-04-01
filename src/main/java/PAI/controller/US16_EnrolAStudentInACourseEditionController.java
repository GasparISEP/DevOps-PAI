package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.*;
import PAI.repository.CourseEditionEnrolmentRepository;
import PAI.repository.CourseEditionRepository;
import PAI.repository.IProgrammeEditionEnrolmentRepository;
import PAI.repository.ProgrammeEditionEnrolmentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class US16_EnrolAStudentInACourseEditionController {

    private final CourseEditionEnrolmentRepository _ceeRepository;
    private final IProgrammeEditionEnrolmentRepository _peeRepository;
    private final CourseEditionRepository _courseEditionRepository;


    public US16_EnrolAStudentInACourseEditionController(
            CourseEditionEnrolmentRepository ceeRepository, IProgrammeEditionEnrolmentRepository peeRepository, CourseEditionRepository courseEditionRepository) {

        validateCourseEditionEnrolmentRepository (ceeRepository);
        validateProgrammeEditionEnrolmentRepository (peeRepository);
        validateCourseEditionRepository (courseEditionRepository);

        this._ceeRepository = ceeRepository;
        this._peeRepository = peeRepository;
        this._courseEditionRepository = courseEditionRepository;
    }

    //show a list of programme editions that student is enrolled
    public List<ProgrammeEdition> findProgrammeEditionsThatStudentIsEnrolled (Student student) {

        if (student == null) {
            return Collections.emptyList();
        }

        return _peeRepository.findProgrammeEditionsThatStudentIsEnrolled (student);
    }

    //show a list of course editions that belongs to a course edition for student choose a course edition
    public List<CourseEdition_2> findCourseEditionsByProgrammeEdition(ProgrammeEdition programmeEdition) {
        return _courseEditionRepository.findCourseEditionsByProgrammeEdition(programmeEdition);
    }

    //enrol a student in a course edition
    public boolean enrolStudentInCourseEdition(StudentID studentId, CourseEditionID courseEditionId) {
        return _ceeRepository.enrolStudentInACourseEdition(studentId, courseEditionId);
    }

    //Verify if the course edition enrollment repository is valid
    private void validateCourseEditionEnrolmentRepository (CourseEditionEnrolmentRepository ceeRepository) throws IllegalArgumentException {
        if (ceeRepository == null) {
            throw new IllegalArgumentException("Course edition enrolment repository cannot be null!");
        }
    }

    //Verify if the programme edition enrollment repo is valid
    private void validateProgrammeEditionEnrolmentRepository (IProgrammeEditionEnrolmentRepository peeRepository) throws IllegalArgumentException {
        if (peeRepository == null) {
            throw new IllegalArgumentException("Programme edition enrolment repository interface cannot be null!");
        }
    }

    //verify if the course edition repository is valid
    private void validateCourseEditionRepository (CourseEditionRepository courseEditionRepository) throws IllegalArgumentException {
        if (courseEditionRepository == null) {
            throw new IllegalArgumentException("Course edition repository cannot be null!");
        }
    }
}
