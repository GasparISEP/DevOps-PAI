package PAI.dto.Programme;

public class ProgrammeRequestDTO {

    private String name;
    private String acronym;
    private int quantECTS;
    private int quantSemesters;
    private String degreeTypeID;
    private String departmentID;
    private String teacherID;

    public ProgrammeRequestDTO(String name, String acronym, int quantECTS, int quantSemesters, String degreeTypeID, String departmentID, String teacherID) {
        this.name = name;
        this.acronym = acronym;
        this.quantECTS = quantECTS;
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

    public int getQuantECTS() {
        return quantECTS;
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
