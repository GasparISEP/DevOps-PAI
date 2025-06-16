package PAI.service.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.assembler.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentAssemblerImpl;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.repositoryInterfaces.student.IStudentRepository;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.student.Student;
import PAI.dto.programmeEdition.ProgrammeEditionWithNameAndDescriptionResponseDTO;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentHateoasResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentProgrammeEditionEnrolmentServiceImpl implements IStudentProgrammeEditionEnrolmentService {

    private final IProgrammeEnrolmentRepository programmeEnrolmentRepository;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository;
    private final IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory;
    private final StudentProgrammeEditionEnrolmentAssemblerImpl programmeEditionAssembler;
    private final IProgrammeEditionControllerAssembler programmeEditionControllerAssembler;
    private final ISchoolYearRepository schoolYearRepository;
    private final IProgrammeRepository programmeRepository;
    private final IStudentRepository studentRepository;
    private final IProgrammeEnrolmentAssembler programmeEnrolmentAssembler;

    public StudentProgrammeEditionEnrolmentServiceImpl(
            IProgrammeEnrolmentRepository programmeEnrolmentRepository,
            IProgrammeEditionRepository programmeEditionRepository,
            IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
            IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory,
            StudentProgrammeEditionEnrolmentAssemblerImpl programmeEditionAssembler,
            IProgrammeEditionControllerAssembler programmeEditionControllerAssembler,
            ISchoolYearRepository schoolYearRepository,IProgrammeRepository programmeRepository,
            IStudentRepository studentRepository,
            IProgrammeEnrolmentAssembler programmeEnrolmentAssembler) {

        this.programmeEnrolmentRepository = programmeEnrolmentRepository;
        this.programmeEditionRepository = programmeEditionRepository;
        this.programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;
        this.programmeEditionEnrolmentFactory = programmeEditionEnrolmentFactory;
        this.programmeEditionAssembler = programmeEditionAssembler;
        this.programmeEditionControllerAssembler = programmeEditionControllerAssembler;
        this.schoolYearRepository = schoolYearRepository;
        this.programmeRepository = programmeRepository;
        this.studentRepository = studentRepository;
        this.programmeEnrolmentAssembler = programmeEnrolmentAssembler;

    }

    @Override
    public List<StudentProgrammeEditionEnrolmentDTO> findAvailableProgrammeEditionsForStudent(StudentID studentID) {
        List<ProgrammeID> programmeIDs = programmeEnrolmentRepository.findProgrammesByStudent(studentID);

        if (programmeIDs.isEmpty()) {
            return List.of();
        }

        List<ProgrammeEdition> editions = programmeEditionRepository.findByProgrammeIDs(programmeIDs);

        return editions.stream()
                .map(programmeEditionAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void enrolStudentInProgrammeEdition(StudentID studentID, ProgrammeID programmeID, SchoolYearID schoolYearID) {
        // Verificar se o estudante está inscrito no programa
        List<ProgrammeID> programmeIDs = programmeEnrolmentRepository.findProgrammesByStudent(studentID);
        if (programmeIDs.stream().noneMatch(p -> p.equals(programmeID))) {
            throw new IllegalArgumentException("Student is not enrolled in the given programme.");
        }

        ProgrammeEditionID programmeEditionID;
        try {
            programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ProgrammeEditionID: " + e.getMessage(), e);
        }

        ProgrammeEdition programmeEdition = programmeEditionRepository.findByID(programmeEditionID)
                .orElseThrow(() -> new IllegalArgumentException("ProgrammeEdition not found."));

        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeEditionID, studentID);

        if (programmeEditionEnrolmentRepository.existsByID(enrolmentID)) {
            throw new IllegalStateException("Student already enrolled in this ProgrammeEdition.");
        }

        ProgrammeEditionEnrolment enrolment = programmeEditionEnrolmentFactory.create(
                enrolmentID, studentID, programmeEditionID
        );

        try {
            programmeEditionEnrolmentRepository.save(enrolment);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to save ProgrammeEditionEnrolment: " + e.getMessage(), e);
        }

    }

    public StudentID findStudentIDByProgrammeEnrolmentGeneratedID(ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID) {
        Optional<ProgrammeEnrolment> programmeEnrolmentOptional = programmeEnrolmentRepository.findByGeneratedID(programmeEnrolmentGeneratedID);

        return programmeEnrolmentOptional.get().getStudentID();
    }

    public LocalDate findDateByProgrammeEnrolmentGeneratedID(ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID) {
        return programmeEnrolmentRepository.findByGeneratedID(programmeEnrolmentGeneratedID)
                .map(enrolment -> enrolment.getDate().getLocalDate())
                .orElseThrow(() -> new IllegalArgumentException("ProgrammeEnrolment not found with ID: " + programmeEnrolmentGeneratedID));
    }

    public ProgrammeID findProgrammeIDByProgrammeEnrolmentGeneratedID(ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID) {
        Optional<ProgrammeEnrolment> programmeEnrolmentOptional = programmeEnrolmentRepository.findByGeneratedID(programmeEnrolmentGeneratedID);

        return programmeEnrolmentOptional.get().getProgrammeID();
    }

    public List<ProgrammeEditionID> getAvailableProgrammeEditions(ProgrammeID programmeID, LocalDate date) {
        return programmeEditionRepository.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(programmeID, date);
    }

    public List<ProgrammeEditionID> getProgrammesEditionsIdWhereStudentIsEnrolled(StudentID studentID) {
        return programmeEditionEnrolmentRepository.findProgrammeEditionsThatStudentIsEnrolled(studentID);
    }

    public List<ProgrammeEditionID> possibleProgrammeEditionsWhereStudentCanBeEnrolled(List<ProgrammeEditionID> programmeEditionsIdsAvailable, List<ProgrammeEditionID> programmeEditionsIdsAlreadyEnrolled) {
        programmeEditionsIdsAvailable.removeAll(programmeEditionsIdsAlreadyEnrolled);
        return programmeEditionsIdsAvailable;
    }

    public ProgrammeEditionWithNameAndDescriptionResponseDTO programmeEditionWithNameAndDescription(ProgrammeEditionID programmeEditionID) {
        Programme programme = getProgramme(programmeEditionID);
        SchoolYear schoolYear = getSchoolYear(programmeEditionID);
        return programmeEditionControllerAssembler.toProgrammeEditionIdResponseDto(programme, schoolYear);
    }

    private Programme getProgramme(ProgrammeEditionID programmeEditionID) {
        ProgrammeEdition programmeEdition = programmeEditionRepository.ofIdentity(programmeEditionID).orElseThrow(() ->
                new IllegalArgumentException("ProgrammeEdition not found: " + programmeEditionID));
        ProgrammeID programmeID = programmeEdition.findProgrammeIDInProgrammeEdition();
        return programmeRepository.ofIdentity(programmeID).orElseThrow(() ->
                new IllegalArgumentException("Programme not found: " + programmeID));

    }

    private SchoolYear getSchoolYear(ProgrammeEditionID programmeEditionID) {
        ProgrammeEdition programmeEdition = programmeEditionRepository.ofIdentity(programmeEditionID).orElseThrow();
        SchoolYearID schoolYearID = programmeEdition.findSchoolYearIDInProgrammeEdition();
        return schoolYearRepository.findBySchoolYearID(schoolYearID).orElseThrow();
    }

    public ProgrammeEnrolmentHateoasResponseDto getProgrammeEnrolmentHateoasInformationDto(ProgrammeEnrolment programmeEnrolment) {
        Programme programme = getProgrammeFromProgrammeEnrolment(programmeEnrolment);
        Student student = getStudentFromProgrammeEnrolment(programmeEnrolment);
        return programmeEnrolmentAssembler.toHateoasDto(programmeEnrolment, student, programme);
    }

    private Programme getProgrammeFromProgrammeEnrolment(ProgrammeEnrolment programmeEnrolment) {
        ProgrammeID programmeID = programmeEnrolment.getProgrammeID();
        return programmeRepository.ofIdentity(programmeID).orElseThrow(() ->
                new IllegalArgumentException("Programme not found: " + programmeID));
    }

    private Student getStudentFromProgrammeEnrolment(ProgrammeEnrolment programmeEnrolment) {
        StudentID studentID = programmeEnrolment.getStudentID();
        return studentRepository.ofIdentity(studentID).orElseThrow(() ->
                new IllegalArgumentException("Programme not found: " + studentID));
    }

}