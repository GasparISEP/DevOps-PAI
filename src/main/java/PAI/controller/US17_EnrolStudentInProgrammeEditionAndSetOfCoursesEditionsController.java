package PAI.controller;

import PAI.VOs.*;
import PAI.repository.*;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;

import java.util.List;
import java.util.Optional;

public class US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController {

    private IProgrammeEditionEnrolmentRepository _programmeEditionEnrolmentRepository;
    private IProgrammeEditionRepository _programmeEditionRepository;
    private IProgrammeRepository _programmeList;
    private ICourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;
    private ICourseEditionRepository _courseEditionRepository;
    private ISchoolYearRepository _schoolYearRepository;
    private IProgrammeEnrolmentRepository _programmeEnrolmentRepository;

    public US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
            IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
            IProgrammeEditionRepository programmeEditionRepository,
            IProgrammeRepository programmeRepository,
            ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository,
            ICourseEditionRepository courseEditionRepository,
            ISchoolYearRepository schoolYearRepository,
            IProgrammeEnrolmentRepository programmeEnrolmentRepository) {

        this._programmeEditionEnrolmentRepository= validateRepository(programmeEditionEnrolmentRepository, "Programme edition enrolment repository");
        this._programmeEditionRepository= validateRepository(programmeEditionRepository, "Programme edition repository");
        this._programmeList= validateRepository(programmeRepository, "Programme list");
        this._courseEditionEnrolmentRepository = validateRepository(courseEditionEnrolmentRepository, "Course edition enrolment repository");
        this._courseEditionRepository= validateRepository(courseEditionRepository, "Course edition repository");
        this._schoolYearRepository= validateRepository(schoolYearRepository, "School year repository");
        this._programmeEnrolmentRepository= validateRepository(programmeEnrolmentRepository, "Enrolment repository");

    }

     public List<ProgrammeID> getAllProgrammesIDs() {
        return _programmeList.getAllProgrammesIDs();
    }

    public List<SchoolYearID> getAllSchoolYearsIDs() {
        return _schoolYearRepository.getAllSchoolYearsIDs();
    }

   // Enroll a student in a ProgrammeEdition.
    public boolean enrolStudentInProgrammeEditionAndSetOfCoursesEditions(
            StudentID studentId, ProgrammeID programmeId, SchoolYearID schoolYearId ) throws Exception{

        if (!_programmeEnrolmentRepository.isStudentEnrolled(studentId, programmeId)) {
            return false;
        }

        // Find ProgrammeEdition
        Optional<ProgrammeEditionID> optionalProgrammeEdition =
                _programmeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);

        if (optionalProgrammeEdition.isEmpty()) {
            return false;
        }

        ProgrammeEditionID programmeEditionId = optionalProgrammeEdition.get();

        // Check if student is already enrolled
        if (_programmeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(studentId, programmeEditionId)) {
            return false;
        }

        // Enroll student in programmeEdition
        _programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentId, programmeEditionId);

        List<CourseEditionID> courseEditions = _courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionId);

        _courseEditionEnrolmentRepository.enrolStudentInProgrammeCourseEditions(studentId, courseEditions);
        return true;
    }

   private <T> T validateRepository(T repository, String name) {
        if (repository == null) {
            throw new IllegalStateException(name + " cannot be null.");
            }
       return repository;
   }

}


