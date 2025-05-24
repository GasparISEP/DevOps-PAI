package PAI.dto.Programme;

public class ProgrammeRequestDTO {

    private String name;
    private String acronym;
    private int maxECTS;
    private int quantSemesters;
    private String degreeTypeID;
    private String departmentID;
    private String teacherID;

    public ProgrammeRequestDTO() {}

    public ProgrammeRequestDTO(String name, String acronym, int maxECTS, int quantSemesters, String degreeTypeID, String departmentID, String teacherID) {
        this.name = name;
        this.acronym = acronym;
        this.maxECTS = maxECTS;
        this.quantSemesters = quantSemesters;
        this.degreeTypeID = degreeTypeID;
        this.departmentID = departmentID;
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public int getMaxECTS() {
        return maxECTS;
    }

    public int getQuantSemesters() {
        return quantSemesters;
    }

    public String getDegreeTypeID() {
        return degreeTypeID;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public String getTeacherID() {
        return teacherID;
    }
}
