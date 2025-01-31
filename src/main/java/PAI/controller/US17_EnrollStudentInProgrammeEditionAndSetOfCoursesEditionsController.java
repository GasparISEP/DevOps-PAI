package PAI.controller;

import PAI.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController {

    private ProgrammeEditionEnrollmentRepo _programmeEditionEnrollmentRepo;
    private ProgrammeEditionRepository _programmeEditionRepository;
    private ProgrammeList _programmeList;
    private CourseEditionEnrollmentRepository _courseEditionEnrollmentRepository;
    private CourseEditionRepository _courseEditionRepository;
    private StudentRepository _studentRepository;

    public US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
            ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo,
            ProgrammeEditionRepository programmeEditionRepository,
            ProgrammeList programmeList,
            CourseEditionEnrollmentRepository courseEditionEnrollmentRepository,
            CourseEditionRepository courseEditionRepository,
            StudentRepository studentRepository) {

        this._programmeEditionEnrollmentRepo = programmeEditionEnrollmentRepo;
        this._programmeEditionRepository = programmeEditionRepository;
        this._programmeList = programmeList;
        this._courseEditionEnrollmentRepository = courseEditionEnrollmentRepository;
        this._courseEditionRepository = courseEditionRepository;
        this._studentRepository = studentRepository;
    }
    // Enroll a student in a ProgrammeEdition.
    public Optional<ProgrammeEdition> enrollStudentInProgrammeEdition(
            int uniqueNumber, Programme programme, SchoolYear schoolYear) {

       Optional<Student> studentOpt = _studentRepository.findStudentByUniqueNumber(uniqueNumber);

        if (studentOpt.isEmpty()) {
            throw new IllegalStateException("Student not found.");
        }

        Student student = studentOpt.get();

        if (!programme.isStudentEnrolled(student)) {
            throw new IllegalStateException("Student not enrolled in Programme.");
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

        // Enroll student
        _programmeEditionEnrollmentRepo.enrollStudentInProgrammeEdition(student, programmeEdition, LocalDate.now());

        return Optional.of(programmeEdition);
    }

    //Enroll a student in all CourseEditions of a ProgrammeEdition.
    public Optional<List<CourseEdition>> enrollStudentInCourseEditions(
            Student student, ProgrammeEdition programmeEdition) {

        // Find all course editions for the programme edition
        List<CourseEdition> courseEditionsOfAProgrammeEdition =
                _courseEditionRepository.findCourseEditionsByProgrammeEdition(programmeEdition);

        for (CourseEdition courseEdition : courseEditionsOfAProgrammeEdition) {
            Optional<CourseEditionEnrollment> existingEnrollment =
                    _courseEditionEnrollmentRepository.findByStudentAndEdition(student, courseEdition);
            if (existingEnrollment.isPresent()) {
                throw new IllegalArgumentException("This course edition enrollment is already in the list.");
            } else {
                _courseEditionEnrollmentRepository.enrollStudentInACourseEdition(student, courseEdition, LocalDate.now());
            }
        }

        return Optional.of(courseEditionsOfAProgrammeEdition);
    }

    public List<Programme> getAllProgrammes() {
        return _programmeList.getAllProgrammes();
    }



}
