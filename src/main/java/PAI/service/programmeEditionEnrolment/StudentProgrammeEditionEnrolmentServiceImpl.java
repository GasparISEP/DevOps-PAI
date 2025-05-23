package PAI.service.programmeEditionEnrolment;

import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.assembler.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentAssemblerImpl;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentProgrammeEditionEnrolmentServiceImpl implements IStudentProgrammeEditionEnrolmentService {

    private final IProgrammeEnrolmentRepository programmeEnrolmentRepository;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final StudentProgrammeEditionEnrolmentAssemblerImpl programmeEditionAssembler;

    public StudentProgrammeEditionEnrolmentServiceImpl(
            IProgrammeEnrolmentRepository programmeEnrolmentRepository,
            IProgrammeEditionRepository programmeEditionRepository,
            StudentProgrammeEditionEnrolmentAssemblerImpl programmeEditionAssembler) {

        this.programmeEnrolmentRepository = programmeEnrolmentRepository;
        this.programmeEditionRepository = programmeEditionRepository;
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
}

