package PAI.dto.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

import java.util.UUID;

public class ProgrammeEnrolmentMapper {

    public ProgrammeEnrolmentMapper() {
    }

    public ProgrammeEnrolment toProgrammeEnrolment (ProgrammeEnrolmentRequestDTO programmeDTO){
        StudentID studentID = new StudentID(programmeDTO.getStudentID());
        AccessMethodID accessMethodID = new AccessMethodID(UUID.fromString(programmeDTO.getAccessMethodID()));
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeDTO.getProgrammeName());
        Acronym acronym = new Acronym(programmeDTO.getProgrammeAcronym());
        ProgrammeID programmeID = new ProgrammeID(name,acronym);
        Date date = new Date(programmeDTO.getDate());

        return new ProgrammeEnrolment(studentID,accessMethodID,programmeID,date);
    }

}
