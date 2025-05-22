package PAI.dto.Programme;

public class ProgrammeResponseDTO {

    private String name;
    private String acronym;
    private int quantECTS;
    private int quantSemesters;
    private String degreeTypeName;
    private String departmentName;
    private String teacherName;

    public ProgrammeResponseDTO() {}

    public ProgrammeResponseDTO(String name, String acronym, int quantECTS, int quantSemesters, String degreeTypeName, String departmentName, String teacherName) {
        this.name = name;
        this.acronym = acronym;
        this.quantECTS = quantECTS;
        this.quantSemesters = quantSemesters;
        this.degreeTypeName = degreeTypeName;
        this.departmentName = departmentName;
        this.teacherName = teacherName;
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

    public String getDegreeTypeName() {
        return degreeTypeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

}
