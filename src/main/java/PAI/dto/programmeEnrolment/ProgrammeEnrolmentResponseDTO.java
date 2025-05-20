package PAI.dto.programmeEnrolment;

import java.time.LocalDate;

public class ProgrammeEnrolmentResponseDTO {

    private String studentName;
    private String accessMethodName;
    private String programmeName;
    private LocalDate date;

    public ProgrammeEnrolmentResponseDTO(String studentName, String accessMethodName, String programmeName, LocalDate date) {
        this.studentName = studentName;
        this.accessMethodName = accessMethodName;
        this.programmeName = programmeName;
        this.date = date;
    }
}
