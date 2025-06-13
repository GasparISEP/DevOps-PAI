package PAI.service.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.assembler.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentAssemblerImpl;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
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

    public StudentProgrammeEditionEnrolmentServiceImpl(
            IProgrammeEnrolmentRepository programmeEnrolmentRepository,
            IProgrammeEditionRepository programmeEditionRepository,
            IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
            IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory,
            StudentProgrammeEditionEnrolmentAssemblerImpl programmeEditionAssembler) {

        this.programmeEnrolmentRepository = programmeEnrolmentRepository;
        this.programmeEditionRepository = programmeEditionRepository;
        this.programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;
        this.programmeEditionEnrolmentFactory = programmeEditionEnrolmentFactory;
        this.programmeEditionAssembler = programmeEditionAssembler;
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

    public ProgrammeID findProgrammeIDByProgrammeEnrolmentGeneratedID (ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID) {
        Optional<ProgrammeEnrolment> programmeEnrolmentOptional = programmeEnrolmentRepository.findByGeneratedID(programmeEnrolmentGeneratedID);

        return programmeEnrolmentOptional.get().getProgrammeID();
    }

    public List<ProgrammeEditionID> getAvailableProgrammeEditions(ProgrammeID programmeID, LocalDate date) {
        return programmeEditionRepository.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(programmeID, date);
    }
}