package PAI.dto.programmeEnrolment;

import java.time.LocalDate;

public class ProgrammeEnrolmentResponseDTO {

    private int studentName;
    private String accessMethodName;
    private String programmeName;
    private LocalDate date;

    public ProgrammeEnrolmentResponseDTO(int studentName, String accessMethodName, String programmeName, LocalDate date) {
        this.studentName = studentName;
        this.accessMethodName = accessMethodName;
        this.programmeName = programmeName;
        this.date = date;
    }
}
