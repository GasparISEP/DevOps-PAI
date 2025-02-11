package PAI.controller;

import PAI.domain.*;

import java.time.LocalDate;

public class US16_EnrollAStudentInACourseEditionController {

    private CourseEditionEnrollmentRepository _ceeRepository;
    private ProgrammeEditionEnrollmentRepo _peeRepository;
    private CourseEditionRepository _courseEditionRepository;


    public US16_EnrollAStudentInACourseEditionController(
            CourseEditionEnrollmentRepository ceeRepository, ProgrammeEditionEnrollmentRepo peeRepository, CourseEditionRepository courseEditionRepository) {

        validateCourseEditionEnrollmentRepository (ceeRepository);
        validateProgrammeEditionEnrollmentRepo (peeRepository);
        validateCourseEditionRepository (courseEditionRepository);
    }

    public boolean enrollStudentInCourseEdition(Student student, CourseEdition courseEdition) throws Exception {
        if (student == null || courseEdition == null){
            return false;
        }

        if (!isStudentEnrolledInProgrammeEdition(student, courseEdition)) {
            return false;
        }

        _ceeRepository.enrollStudentInACourseEdition(student, courseEdition, LocalDate.now());
        return true;
    }

    // Verify if student belongs to programme edition that has the course edition passed as an attribute
    private boolean isStudentEnrolledInProgrammeEdition(Student student, CourseEdition courseEdition) throws Exception {
        ProgrammeEdition programmeEdition = _courseEditionRepository.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition);

        return _peeRepository.isStudentEnrolledInThisProgrammeEdition(student, programmeEdition);
    }

    //Verify if the course edition enrollment repository is valid
    private void validateCourseEditionEnrollmentRepository (CourseEditionEnrollmentRepository ceeRepository) throws IllegalArgumentException {
        if (ceeRepository == null) {
            throw new IllegalArgumentException("Course edition enrollment repository cannot be null!");
        }
        this._ceeRepository = ceeRepository;
    }

    //Verify if the programme edition enrollment repo is valid
    private void validateProgrammeEditionEnrollmentRepo (ProgrammeEditionEnrollmentRepo peeRepository) throws IllegalArgumentException {
        if (peeRepository == null) {
            throw new IllegalArgumentException("Programme edition enrollment repository cannot be null!");
        }
        this._peeRepository = peeRepository;
    }

    //verify if the course edition repository is valid
    private void validateCourseEditionRepository (CourseEditionRepository courseEditionRepository) throws IllegalArgumentException {
        if (courseEditionRepository == null) {
            throw new IllegalArgumentException("Course edition repository cannot be null!");
        }
        this._courseEditionRepository = courseEditionRepository;
    }
}
