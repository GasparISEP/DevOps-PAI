package PAI.dto.programmeEnrolment;

import java.time.LocalDate;

public class ProgrammeEnrolmentResponseDTO {

    private int studentID;
    private String accessMethodID;
    private String programmeID;
    private LocalDate date;

    public ProgrammeEnrolmentResponseDTO(int studentID, String accessMethodID, String programmeID, LocalDate date) {
        this.studentID = studentID;
        this.accessMethodID = accessMethodID;
        this.programmeID = programmeID;
        this.date = date;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public String getAccessMethodID() {
        return accessMethodID;
    }

    public LocalDate getDate() {
        return date;
    }
}
