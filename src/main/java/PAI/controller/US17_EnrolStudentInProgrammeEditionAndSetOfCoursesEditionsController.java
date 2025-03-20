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
            ProgrammeRepository programmeRepository,
            CourseEditionEnrolmentRepository courseEditionEnrolmentRepository,
            CourseEditionRepository courseEditionRepository,
            SchoolYearRepository schoolYearRepository,
            ProgrammeEnrolmentRepository programmeEnrolmentRepository) {

        this._programmeEditionEnrolmentRepository= validateRepository(programmeEditionEnrolmentRepository, "Programme edition enrolment repository");
        this._programmeEditionRepository= validateRepository(programmeEditionRepository, "Programme edition repository");
        this._programmeList= validateRepository(programmeRepository, "Programme list");
        this._courseEditionEnrolmentRepository= validateRepository(courseEditionEnrolmentRepository, "Course edition enrolment repository");
        this._courseEditionRepository= validateRepository(courseEditionRepository, "Course edition repository");
        this._schoolYearRepository= validateRepository(schoolYearRepository, "School year repository");
        this._programmeEnrolmentRepository= validateRepository(programmeEnrolmentRepository, "Enrolment repository");

    }

    public List<Programme> getAllProgrammes() {
        return _programmeList.getAllProgrammes();
    }

    public List<SchoolYear> getAllSchoolYears() {
        return _schoolYearRepository.getAllSchoolYears();
    }

    // Enroll a student in a ProgrammeEdition.
    public boolean enrolStudentInProgrammeEditionAndSetOfCoursesEditions(
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
        _programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(student, programmeEdition);

        List<CourseEdition> courseEditions = _courseEditionRepository.findCourseEditionsByProgrammeEdition(programmeEdition);

        _courseEditionEnrolmentRepository.enrolStudentInProgrammeCourseEditions(student, courseEditions);
        return true;
    }

    private <T> T validateRepository(T repository, String name) {
        if (repository == null) {
            throw new IllegalStateException(name + " cannot be null.");
        }
        return repository;
    }

}


