package PAI.dto.programmeEditionEnrolment;

public class ProgrammeEditionEnrolmentRequest {
    private String studentId;
    private String programmeAcronym;
    private String programmeName;
    private String schoolYearId;

    // Construtor vazio obrigatório para o Jackson
    public ProgrammeEditionEnrolmentRequest() {
    }

    // Construtor com argumentos (opcional, útil para testes manuais ou mocks)
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

    // Setters
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setProgrammeAcronym(String programmeAcronym) {
        this.programmeAcronym = programmeAcronym;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public void setSchoolYearId(String schoolYearId) {
        this.schoolYearId = schoolYearId;
    }
}
