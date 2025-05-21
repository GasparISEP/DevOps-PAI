package PAI.dto.Programme;

import PAI.VOs.*;

public class ProgrammeVOsDTO {

    private NameWithNumbersAndSpecialChars name;
    private Acronym acronym;
    private QuantEcts quantEcts;
    private QuantSemesters quantSemesters;
    private DegreeTypeID degreeTypeID;
    private DepartmentID departmentID;
    private TeacherID teacherID;

    public ProgrammeVOsDTO(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantEcts, QuantSemesters quantSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID teacherID) {
        this.name = name;
        this.acronym = acronym;
        this.quantEcts = quantEcts;
        this.quantSemesters = quantSemesters;
        this.degreeTypeID = degreeTypeID;
        this.departmentID = departmentID;
        this.teacherID = teacherID;
    }

    public NameWithNumbersAndSpecialChars getName() {
        return name;
    }

    public Acronym getAcronym() {
        return acronym;
    }

    public QuantEcts getQuantEcts() {
        return quantEcts;
    }

    public QuantSemesters getQuantSemesters() {
        return quantSemesters;
    }

    public DegreeTypeID getDegreeTypeID() {
        return degreeTypeID;
    }

    public DepartmentID getDepartmentID() {
        return departmentID;
    }

    public TeacherID getTeacherID() {
        return teacherID;
    }
}
