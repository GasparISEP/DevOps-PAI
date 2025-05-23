package PAI.dto.programmeEditionEnrolment;

public class ProgrammeEditionEnrolmentRequest {
    private String studentId;
    private String programmeAcronym;
    private String programmeName;
    private String schoolYearId;

    // Construtor
    public ProgrammeEditionEnrolmentRequest(String studentId, String programmeAcronym, String programmeName, String schoolYearId) {
        this.studentId = studentId;
        this.programmeAcronym = programmeAcronym;
        this.programmeName = programmeName;
        this.schoolYearId = schoolYearId;
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getProgrammeAcronym() {
        return programmeAcronym;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public String getSchoolYearId() {
        return schoolYearId;
    }
}
