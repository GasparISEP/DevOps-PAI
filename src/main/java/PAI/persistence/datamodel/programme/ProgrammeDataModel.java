package PAI.persistence.datamodel.programme;

import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import jakarta.persistence.*;

@Entity
@Table(name = "Programme")
public class ProgrammeDataModel {

    @EmbeddedId
    private ProgrammeIDDataModel programmeID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Acronym")
    private String acronym;

    @Column(name = "QuantSemesters")
    private int quantSemesters;

    @Column(name = "QuantEcts")
    private int quantEcts;

    @Column(name = "DegreeTypeID")
    private DegreeTypeIDDataModel degreeTypeID;

    @Embedded
    @Column(name = "DepartmentID")
    private DepartmentIDDataModel departmentID;

    @Embedded
    @Column(name = "ProgDirectorID")
    private TeacherIDDataModel programmeDirectorID;

    public ProgrammeDataModel() {}

    public ProgrammeDataModel(ProgrammeIDDataModel progID, String name, String acronym, int quantSemesters, int quantEcts, DegreeTypeIDDataModel degreeTypeID, DepartmentIDDataModel departmentID, TeacherIDDataModel progDirectorID) {

        if (name == null || acronym == null || degreeTypeID == null || departmentID == null || progDirectorID == null || progID == null) {
            throw new IllegalArgumentException("Attributes cannot be null");
        }

        if (quantEcts <= 0 || quantSemesters <= 0) {
            throw new IllegalArgumentException("Attributes must be above 0");
        }

        this.programmeID = progID;
        this.name = name;
        this.acronym = acronym;
        this.quantSemesters = quantSemesters;
        this.quantEcts = quantEcts;
        this.degreeTypeID = degreeTypeID;
        this.departmentID = departmentID;
        this.programmeDirectorID = progDirectorID;
    }

    public String getName(){
        return name;
    }

    public String getAcronym(){
        return acronym;
    }

    public int getQuantSemesters(){
        return quantSemesters;
    }

    public int getQuantEcts(){
        return quantEcts;
    }

    public DegreeTypeIDDataModel getDegreeTypeID(){
        return degreeTypeID;
    }

    public DepartmentIDDataModel getDepartmentID(){
        return departmentID;
    }

    public TeacherIDDataModel getProgrammeDirectorID(){
        return programmeDirectorID;
    }

    public ProgrammeIDDataModel getProgID() {
        return programmeID;
    }
}