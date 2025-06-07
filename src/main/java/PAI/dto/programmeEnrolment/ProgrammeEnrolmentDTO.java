package PAI.dto.programmeEnrolment;

import java.time.LocalDate;

public class ProgrammeEnrolmentDTO {
    private int studentID;
    private String accessMethodID;
    private String programmeAcronym;
    private LocalDate date;

    public ProgrammeEnrolmentDTO(int studentID, String accessMethodID, String programmeAcronym, LocalDate date) {
        this.studentID = studentID;
        this.accessMethodID = accessMethodID;
        this.programmeAcronym = programmeAcronym;
        this.date = date;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getAccessMethodID() {
        return accessMethodID;
    }

    public String getProgrammeAcronym() {
        return programmeAcronym;
    }

    public LocalDate getDate() {
        return date;
    }
}
