package PAI.dto.Programme;

public class ProgrammeDirectorRequestDTO {

    private String programmeName;
    private String programmeAcronym;
    private String teacherAcronym;

    public ProgrammeDirectorRequestDTO() {}

    public ProgrammeDirectorRequestDTO (String programmeName, String programmeAcronym, String teacherAcronym) {
        this.programmeName = programmeName;
        this.programmeAcronym = programmeAcronym;
        this.teacherAcronym = teacherAcronym;

    }

    public String getProgrammeName() {
        return programmeName;
    }

    public String getProgrammeAcronym() {
        return programmeAcronym;
    }

    public String getTeacherAcronym() {
        return teacherAcronym;
    }
}
