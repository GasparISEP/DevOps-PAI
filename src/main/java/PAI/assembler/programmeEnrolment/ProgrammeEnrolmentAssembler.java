package PAI.assembler.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.student.Student;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
}