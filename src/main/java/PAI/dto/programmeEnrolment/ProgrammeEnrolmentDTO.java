package PAI.dto.programmeEnrolment;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class ProgrammeEnrolmentDTO {
    private int studentID;
    private String accessMethodID;
    private String programmeAcronym;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    public ProgrammeEnrolmentDTO() {}

    public ProgrammeEnrolmentDTO(int studentID, String accessMethodID, String programmeAcronym, LocalDate date) {
        this.studentID = studentID;
        this.accessMethodID = accessMethodID;
        this.programmeAcronym = programmeAcronym;
        this.date = date;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getAccessMethodID() {
        return accessMethodID;
    }

    public void setAccessMethodID(String accessMethodID) {
        this.accessMethodID = accessMethodID;
    }

    public String getProgrammeAcronym() {
        return programmeAcronym;
    }

    public void setProgrammeAcronym(String programmeAcronym) {
        this.programmeAcronym = programmeAcronym;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
