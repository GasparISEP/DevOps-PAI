package PAI.controller;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.domain.programme.ProgrammeDDD;
import PAI.repository.*;
import PAI.repository.programmeEditionRepository.ProgrammeEditionRepositoryDDDImpl;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController {

    private ProgrammeEditionEnrolmentRepository _programmeEditionEnrolmentRepository;
    private ProgrammeEditionRepositoryDDDImpl _programmeEditionRepository;
    private ProgrammeDDDRepositoryImpl _programmeList;
    private CourseEditionEnrolmentRepositoryImpl _courseEditionEnrolmentRepositoryImpl;
    private CourseEditionRepositoryDDDImpl _courseEditionRepositoryImpl;
    private SchoolYearRepository _schoolYearRepository;
    private ProgrammeEnrolmentRepository _programmeEnrolmentRepository;

    public US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
            ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
            ProgrammeEditionRepositoryDDDImpl programmeEditionRepository,
            ProgrammeDDDRepositoryImpl programmeRepository,
            CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl,
            CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl,
            SchoolYearRepository schoolYearRepository,
            ProgrammeEnrolmentRepository programmeEnrolmentRepository) {

        this._programmeEditionEnrolmentRepository= validateRepository(programmeEditionEnrolmentRepository, "Programme edition enrolment repository");
        this._programmeEditionRepository= validateRepository(programmeEditionRepository, "Programme edition repository");
        this._programmeList= validateRepository(programmeRepository, "Programme list");
        this._courseEditionEnrolmentRepositoryImpl = validateRepository(courseEditionEnrolmentRepositoryImpl, "Course edition enrolment repository");
        this._courseEditionRepositoryImpl= validateRepository(courseEditionRepositoryImpl, "Course edition repository");
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
            StudentID studentId, ProgrammeID programmeId, SchoolYearID schoolYearId ) {

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

        List<CourseEditionID> courseEditions = _courseEditionRepositoryImpl.findCourseEditionsByProgrammeEdition(programmeEditionId);

        _courseEditionEnrolmentRepositoryImpl.enrolStudentInProgrammeCourseEditions(studentId, courseEditions);
        return true;
    }

   private <T> T validateRepository(T repository, String name) {
        if (repository == null) {
            throw new IllegalStateException(name + " cannot be null.");
            }
       return repository;
   }

}


