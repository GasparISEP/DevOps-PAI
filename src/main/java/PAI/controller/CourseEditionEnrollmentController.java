package PAI.controller;

import PAI.domain.*;

import java.time.LocalDate;
import java.util.Optional;

public class CourseEditionEnrollmentController {

    private CourseEditionEnrollmentRepository _ceeRepository;
    private ProgrammeEditionEnrollmentRepo _peeRepository;
    private CourseEditionRepository _courseEditionRepository;


    public CourseEditionEnrollmentController(
            CourseEditionEnrollmentRepository ceeRepository,
            ProgrammeEditionEnrollmentRepo peeRepository,
            CourseEditionRepository courseEditionRepository) {

        this._ceeRepository = ceeRepository;
        this._peeRepository = peeRepository;
        this._courseEditionRepository = courseEditionRepository;
    }


    public Optional<CourseEditionEnrollment> enrollStudentInCourseEdition(Student student, CourseEdition courseEdition, LocalDate enrollmentDate) throws Exception {

        if (!isStudentInProgrammeEdition(student, courseEdition)) {
            return Optional.empty();
        }

        return _ceeRepository.enrollStudentInACourseEdition(student, courseEdition, enrollmentDate);
    }

    // Verify if student belongs to programme edition that has the course edition passed as an attribute
    private boolean isStudentInProgrammeEdition(Student student, CourseEdition courseEdition) throws Exception {
        ProgrammeEdition programmeEdition = _courseEditionRepository.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition);

        return _peeRepository.isStudentEnrolledInThisProgrammeEdition(student, programmeEdition);
    }
}
