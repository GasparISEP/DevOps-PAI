package PAI.dto.programmeEditionEnrolment;


public class StudentProgrammeEditionEnrolmentDTO {
    private String programmeAcronym;
    private String schoolYearId;

    public StudentProgrammeEditionEnrolmentDTO(String programmeAcronym, String schoolYearId) {
        this.programmeAcronym = programmeAcronym;
        this.schoolYearId = schoolYearId;
    }

    public String getProgrammeAcronym() {
        return programmeAcronym;
    }

    public String getSchoolYearId() {
        return schoolYearId;
    }
}
