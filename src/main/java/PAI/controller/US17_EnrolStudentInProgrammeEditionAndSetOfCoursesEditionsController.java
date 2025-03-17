package PAI.controller;

import PAI.domain.*;
import PAI.repository.*;

import java.util.List;
import java.util.Optional;

public class US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController {

    private ProgrammeEditionEnrolmentRepository _programmeEditionEnrolmentRepository;
    private ProgrammeEditionRepository _programmeEditionRepository;
    private ProgrammeRepository _programmeList;
    private CourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;
    private CourseEditionRepository _courseEditionRepository;
    private SchoolYearRepository _schoolYearRepository;
    private ProgrammeEnrolmentRepository _programmeEnrolmentRepository;

    public US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
            ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
            ProgrammeEditionRepository programmeEditionRepository,
            ProgrammeRepository programmeList,
            CourseEditionEnrolmentRepository courseEditionEnrolmentRepository,
            CourseEditionRepository courseEditionRepository,
            SchoolYearRepository schoolYearRepository,
            ProgrammeEnrolmentRepository programmeEnrolmentRepository) {

        validateProgrammeEditionEnrollmentRepo(programmeEditionEnrolmentRepository);
        validateProgrammeEditionRepository(programmeEditionRepository);
        validateProgrammeList(programmeList);
        validateCourseEditionEnrollmentRepository(courseEditionEnrolmentRepository);
        validateCourseEditionRepository(courseEditionRepository);
        validateSchoolYearRepository(schoolYearRepository);
        validateEnrolmentRepository(programmeEnrolmentRepository);
    }

    public List<Programme> getAllProgrammes() {
        return _programmeList.getAllProgrammes();
    }

    public List<SchoolYear> getAllSchoolYears() {
        return _schoolYearRepository.getAllSchoolYears();
    }

    // Enroll a student in a ProgrammeEdition.
    public boolean enrollStudentInProgrammeEditionAndSetOfCoursesEditions(
            Student student, Programme programme, SchoolYear schoolYear ) {

        if (!_programmeEnrolmentRepository.isStudentEnrolled(student, programme)) {
            return false;
        }

        // Find ProgrammeEdition
        Optional<ProgrammeEdition> optionalProgrammeEdition =
                _programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme, schoolYear);

        if (optionalProgrammeEdition.isEmpty()) {
            return false;
        }

        ProgrammeEdition programmeEdition = optionalProgrammeEdition.get();

        // Check if student is already enrolled
        if (_programmeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(student, programmeEdition)) {
            return false;
        }

        // Enroll student in programmeEdition
        _programmeEditionEnrolmentRepository.enrollStudentInProgrammeEdition(student, programmeEdition);

        List<CourseEdition> courseEditions = _courseEditionRepository.findCourseEditionsByProgrammeEdition(programmeEdition);

        _courseEditionEnrolmentRepository.enrollStudentInProgrammeCourseEditions(student, courseEditions);
        return true;
    }

    //Verify if the programme edition enrollment repo is valid
    private void validateProgrammeEditionEnrollmentRepo(ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository) {
        if (programmeEditionEnrolmentRepository == null) {
            throw new IllegalStateException("Programme edition enrollment repository cannot be null.");
        }
        this._programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;
    }
    //Verify if the programme edition repository is valid
    private void validateProgrammeEditionRepository(ProgrammeEditionRepository programmeEditionRepository) {
        if (programmeEditionRepository == null) {
            throw new IllegalStateException("Programme edition repository cannot be null.");
        }
        this._programmeEditionRepository = programmeEditionRepository;
    }
    //Verify if the programme list is valid
    private void validateProgrammeList(ProgrammeRepository programmeList) {
        if (programmeList == null) {
            throw new IllegalStateException("Programme list cannot be null.");
        }
        this._programmeList = programmeList;
    }
    //Verify if the course edition enrollment repository is valid
    private void validateCourseEditionEnrollmentRepository(CourseEditionEnrolmentRepository courseEditionEnrolmentRepository) {
        if (courseEditionEnrolmentRepository == null) {
            throw new IllegalStateException("Course edition enrollment repository cannot be null.");
        }
        this._courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
    }
    //verify if the course edition repository is valid
    private void validateCourseEditionRepository(CourseEditionRepository courseEditionRepository) {
        if(courseEditionRepository == null) {
            throw new IllegalStateException("Course edition repository cannot be null.");
        }
        this._courseEditionRepository = courseEditionRepository;
    }
    //verify if the school year repository is valid
    private void validateSchoolYearRepository(SchoolYearRepository schoolYearRepository) {
        if(schoolYearRepository == null) {
            throw new IllegalStateException("School year repository cannot be null.");
        }
        this._schoolYearRepository = schoolYearRepository;
    }
    //Verify if the enrollment repository is valid
    private void validateEnrolmentRepository(ProgrammeEnrolmentRepository enrolmentRepository) {
        if (enrolmentRepository == null) {
            throw new IllegalStateException("Enrolment repository cannot be null.");
        }
        this._programmeEnrolmentRepository = enrolmentRepository;
    }

}


