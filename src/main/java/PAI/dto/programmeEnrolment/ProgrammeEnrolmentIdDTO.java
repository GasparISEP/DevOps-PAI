package PAI.dto.programmeEnrolment;

import java.util.UUID;

public class ProgrammeEnrolmentIdDTO {
    private UUID programmeEnrolmentGID;


    public ProgrammeEnrolmentIdDTO() {}

    public ProgrammeEnrolmentIdDTO (UUID programmeEnrolmentGID) {
        this.programmeEnrolmentGID = programmeEnrolmentGID;

    }

    public UUID getProgrammeEnrolmentGID() {return programmeEnrolmentGID;}


}
