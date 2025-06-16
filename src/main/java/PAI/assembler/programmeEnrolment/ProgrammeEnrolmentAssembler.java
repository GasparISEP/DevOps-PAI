package PAI.assembler.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.student.Student;
import PAI.dto.programmeEnrolment.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ProgrammeEnrolmentAssembler implements IProgrammeEnrolmentAssembler {

    public ProgrammeEnrolmentAssembler() {
    }

    public ProgrammeEnrolmentResponseDTO toProgrammeEnrolmentDTO (ProgrammeEnrolment programmeEnrolment){
        UUID gid = programmeEnrolment.getProgrammeEnrolmentGeneratedID().getProgrammeEnrolmentGID();
        int studentID = programmeEnrolment.getStudentID().getUniqueNumber();
        String accessMethodId = programmeEnrolment.getAccessMethodID().toString();
        String programmeAcronym = programmeEnrolment.getProgrammeID().getProgrammeAcronym();
        LocalDate date = programmeEnrolment.getDate().getLocalDate();

        return new ProgrammeEnrolmentResponseDTO(gid,studentID,accessMethodId,programmeAcronym,date);

    }

    public StudentID toStudentID (ProgrammeEnrolmentDTO programmeDTO){
        return new StudentID(programmeDTO.getStudentID());
    }

    public AccessMethodID toAccessMethodID (ProgrammeEnrolmentDTO programmeDTO){
        return new AccessMethodID(UUID.fromString(programmeDTO.getAccessMethodID()));
    }

    public ProgrammeID toProgrammeID (ProgrammeEnrolmentDTO programmeDTO){
        Acronym acronym = new Acronym(programmeDTO.getProgrammeAcronym());

        return new ProgrammeID(acronym);
    }

    public Date toDateVO (ProgrammeEnrolmentDTO programmeDTO){
        return new Date(programmeDTO.getDate());
    }

    public ProgrammeEnrolmentGeneratedID toProgrammeEnrolmentGeneratedID (ProgrammeEnrolmentIdDTO programmeEnrolmentIdDTO) {

        UUID programmeUUID = programmeEnrolmentIdDTO.getProgrammeEnrolmentGID();
       return new ProgrammeEnrolmentGeneratedID(programmeUUID);

    }

    @Override
    public ProgrammeEnrolmentListIDDTO toDTO(ProgrammeEnrolment programmeEnrolment) {
        if (programmeEnrolment == null) {
            throw new IllegalArgumentException("Programme ID cannot be null");
        }
        String programmeAcronym = programmeEnrolment.getProgrammeID().getProgrammeAcronym();
        String programmeEnrolmentGeneratedID = programmeEnrolment.getProgrammeEnrolmentGeneratedID().getProgrammeEnrolmentGID().toString();
        return new ProgrammeEnrolmentListIDDTO(programmeAcronym, programmeEnrolmentGeneratedID);
    }

    @Override
    public List<ProgrammeEnrolmentListIDDTO> toListOfDTOs(List<ProgrammeEnrolment> listIDs) {
        List<ProgrammeEnrolmentListIDDTO> listProg = new ArrayList<>();
        for (ProgrammeEnrolment existing : listIDs) {
            listProg.add(toDTO((existing)));
        }
        return listProg;
    }

    @Override
    public ProgrammeEnrolmentHateoasResponseDto toHateoasDto(ProgrammeEnrolment programmeEnrolment, Student student, Programme programme) {
        UUID gid = programmeEnrolment.getProgrammeEnrolmentGeneratedID().getProgrammeEnrolmentGID();
        int studentID = programmeEnrolment.getStudentID().getUniqueNumber();
        String accessMethodId = programmeEnrolment.getAccessMethodID().toString();
        String programmeAcronym = programmeEnrolment.getProgrammeID().getProgrammeAcronym();
        LocalDate date = programmeEnrolment.getDate().getLocalDate();
        String studentName = programme.getProgrammeName().getNameWithNumbersAndSpecialChars();
        String programmeName = student.getStudentName().getName();

        return new ProgrammeEnrolmentHateoasResponseDto(gid, programmeAcronym, studentID, accessMethodId, date, studentName, programmeName);
    }
}