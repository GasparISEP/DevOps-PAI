package PAI.dto;

import java.time.LocalDate;

public class ProgrammeEnrolmentRequestDTO {
    private int studentID;
    private String accessMethodID;
    private String programmeName;
    private String programmeAcronym;
    private LocalDate date;

    public ProgrammeEnrolmentRequestDTO(int studentID, String accessMethodID, String programmeName, String programmeAcronym, LocalDate date) {
        this.studentID = studentID;
        this.accessMethodID = accessMethodID;
        this.programmeName = programmeName;
        this.programmeAcronym = programmeAcronym;
        this.date = date;
    }


}
