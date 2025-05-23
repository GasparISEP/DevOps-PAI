package PAI.dto.programmeEditionEnrolment;


public class StudentProgrammeEditionEnrolmentDTO {
    private String programmeAcronym;
    private String programmeName;
    private String schoolYearId;

    public StudentProgrammeEditionEnrolmentDTO(String programmeAcronym, String programmeName, String schoolYearId) {
        this.programmeAcronym = programmeAcronym;
        this.programmeName = programmeName;
        this.schoolYearId = schoolYearId;
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
