package PAI.dto.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class ProgrammeEnrolmentAssembler implements IProgrammeEnrolmentAssembler {

    public ProgrammeEnrolmentAssembler() {
    }

    public ProgrammeEnrolment toProgrammeEnrolment (ProgrammeEnrolmentDTO programmeDTO){
        StudentID studentID = new StudentID(programmeDTO.getStudentID());
        AccessMethodID accessMethodID = new AccessMethodID(UUID.fromString(programmeDTO.getAccessMethodID()));
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeDTO.getProgrammeName());
        Acronym acronym = new Acronym(programmeDTO.getProgrammeAcronym());
        ProgrammeID programmeID = new ProgrammeID(name,acronym);
        Date date = new Date(programmeDTO.getDate());

        return new ProgrammeEnrolment(studentID,accessMethodID,programmeID,date);
    }

    public ProgrammeEnrolmentResponseDTO toProgrammeEnrolmentDTO (ProgrammeEnrolment programmeEnrolment){
        int studentID = programmeEnrolment.getStudentID().getUniqueNumber();
        String accessMethodId = programmeEnrolment.getAccessMethodID().toString();
        String programmeName = programmeEnrolment.getProgrammeID().getName().getnameWithNumbersAndSpecialChars();
        LocalDate date = programmeEnrolment.getDate().getLocalDate();

        return new ProgrammeEnrolmentResponseDTO(studentID,accessMethodId,programmeName,date);

    }

    public StudentID toStudentID (ProgrammeEnrolmentDTO programmeDTO){
        return new StudentID(programmeDTO.getStudentID());
    }

    public AccessMethodID toAccessMethodID (ProgrammeEnrolmentDTO programmeDTO){
        return new AccessMethodID(UUID.fromString(programmeDTO.getAccessMethodID()));
    }

    public ProgrammeID toProgrammeID (ProgrammeEnrolmentDTO programmeDTO){
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeDTO.getProgrammeName());
        Acronym acronym = new Acronym(programmeDTO.getProgrammeAcronym());

        return new ProgrammeID(name,acronym);
    }

    public Date toDateVO (ProgrammeEnrolmentDTO programmeDTO){
        return new Date(programmeDTO.getDate());
    }

}
