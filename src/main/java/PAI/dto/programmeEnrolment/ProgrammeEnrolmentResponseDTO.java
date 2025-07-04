package PAI.dto.programmeEnrolment;

import java.time.LocalDate;
import java.util.UUID;

public class ProgrammeEnrolmentResponseDTO {

    private UUID programmeEnrolmentGID;
    private int studentID;
    private String accessMethodID;
    private String programmeID;
    private LocalDate date;

    public ProgrammeEnrolmentResponseDTO(UUID programmeEnrolmentGID, int studentID, String accessMethodID, String programmeID, LocalDate date) {
        this.programmeEnrolmentGID = programmeEnrolmentGID;
        this.studentID = studentID;
        this.accessMethodID = accessMethodID;
        this.programmeID = programmeID;
        this.date = date;
    }

    public UUID getProgrammeEnrolmentGID() {
        return programmeEnrolmentGID;
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
