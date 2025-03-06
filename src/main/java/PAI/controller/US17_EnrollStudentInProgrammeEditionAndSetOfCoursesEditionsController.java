package PAI.controller;

import PAI.domain.*;
import PAI.repository.ProgrammeEditionEnrollmentRepo;
import PAI.repository.ProgrammeEditionRepository;
import PAI.repository.ProgrammeList;
import PAI.repository.CourseEditionEnrollmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController {

    private ProgrammeEditionEnrollmentRepo _programmeEditionEnrollmentRepo;
    private ProgrammeEditionRepository _programmeEditionRepository;
    private ProgrammeList _programmeList;
    private CourseEditionEnrollmentRepository _courseEditionEnrollmentRepository;
    private CourseEditionRepository _courseEditionRepository;
    private SchoolYearRepository _schoolYearRepository;
    private ProgrammeEnrolmentRepository _programmeEnrolmentRepository;

    public US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
            ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo,
            ProgrammeEditionRepository programmeEditionRepository,
            ProgrammeList programmeList,
            CourseEditionEnrollmentRepository courseEditionEnrollmentRepository,
            CourseEditionRepository courseEditionRepository,
            SchoolYearRepository schoolYearRepository,
            ProgrammeEnrolmentRepository programmeEnrolmentRepository) {

        validateProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentRepo);
        validateProgrammeEditionRepository(programmeEditionRepository);
        validateProgrammeList(programmeList);
        validateCourseEditionEnrollmentRepository(courseEditionEnrollmentRepository);
        validateCourseEditionRepository(courseEditionRepository);
        validateSchoolYearRepository(schoolYearRepository);
        validateEnrolmentRepository(programmeEnrolmentRepository);
    }

    // Enroll a student in a ProgrammeEdition.
    public boolean enrollStudentInProgrammeEditionAndSetOfCoursesEditions(
            Student student, Programme programme, SchoolYear schoolYear ) {

        if (!_programmeEnrolmentRepository.isStudentEnrolled(student, programme)) {
            throw new IllegalStateException("Student should be enrolled in Programme.");
        }

        // Find ProgrammeEdition
        Optional<ProgrammeEdition> optionalProgrammeEdition =
                _programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme, schoolYear);

        if (optionalProgrammeEdition.isEmpty()) {
            throw new IllegalStateException("Programme edition not found for the given school year.");
        }

        ProgrammeEdition programmeEdition = optionalProgrammeEdition.get();

        // Check if student is already enrolled
        if (_programmeEditionEnrollmentRepo.isStudentEnrolledInThisProgrammeEdition(student, programmeEdition)) {
            throw new IllegalStateException("Student is already enrolled in the programme edition.");
        }

        // Enroll student in programmeEdition
        _programmeEditionEnrollmentRepo.enrollStudentInProgrammeEdition(student, programmeEdition);

        List<CourseEdition> courseEditions = _courseEditionRepository.findCourseEditionsByProgrammeEdition(programmeEdition);

        _courseEditionEnrollmentRepository.enrollStudentInProgrammeCourseEditions(student, courseEditions);
        return true;
    }

    public List<Programme> getAllProgrammes() {
        return _programmeList.getAllProgrammes();
    }

    public List<SchoolYear> getAllSchoolYears() {
        return _schoolYearRepository.getAllSchoolYears();
    }

    //Verify if the programme edition enrollment repo is valid
    private void validateProgrammeEditionEnrollmentRepo(ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo) {
        if (programmeEditionEnrollmentRepo == null) {
            throw new IllegalStateException("Programme edition enrollment repository cannot be null.");
        }
        this._programmeEditionEnrollmentRepo = programmeEditionEnrollmentRepo;
    }
    //Verify if the programme edition repository is valid
    private void validateProgrammeEditionRepository(ProgrammeEditionRepository programmeEditionRepository) {
        if (programmeEditionRepository == null) {
            throw new IllegalStateException("Programme edition repository cannot be null.");
        }
        this._programmeEditionRepository = programmeEditionRepository;
    }
    //Verify if the programme list is valid
    private void validateProgrammeList(ProgrammeList programmeList) {
        if (programmeList == null) {
            throw new IllegalStateException("Programme list cannot be null.");
        }
        this._programmeList = programmeList;
    }
    //Verify if the course edition enrollment repository is valid
    private void validateCourseEditionEnrollmentRepository(CourseEditionEnrollmentRepository courseEditionEnrollmentRepository) {
        if (courseEditionEnrollmentRepository == null) {
            throw new IllegalStateException("Course edition enrollment repository cannot be null.");
        }
        this._courseEditionEnrollmentRepository = courseEditionEnrollmentRepository;
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

    //Verify if the enrolment list is valid
    private void validateEnrolmentRepository(ProgrammeEnrolmentRepository enrolmentRepository) {
        if (enrolmentRepository == null) {
            throw new IllegalStateException("Enrolment repository cannot be null.");
        }
        this._programmeEnrolmentRepository = enrolmentRepository;
    }
}


