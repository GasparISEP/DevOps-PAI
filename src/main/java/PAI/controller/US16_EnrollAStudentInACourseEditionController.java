package PAI.controller;

import PAI.domain.*;
import PAI.repository.CourseEditionEnrollmentRepository;
import PAI.repository.CourseEditionRepository;
import PAI.repository.ProgrammeEditionEnrollmentRepo;

import java.util.List;

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

    //show a list of course editions that belongs to a course edition for student choose a course edition
    public List<CourseEdition> getCourseEditionsOfProgrammeEdition (ProgrammeEdition programmeEdition) {
        return _courseEditionRepository.findCourseEditionsByProgrammeEdition(programmeEdition);
    }

    //enroll a student in a course edition
    public boolean enrollStudentInCourseEdition(Student student, CourseEdition courseEdition) throws Exception {
        if (student == null || courseEdition == null){
            return false;
        }

        _ceeRepository.enrollStudentInACourseEdition(student, courseEdition);
        return true;
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
