package PAI.persistence.datamodel.programme;

import PAI.persistence.datamodel.degreeType.DegreeTypeIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import jakarta.persistence.*;

import java.util.Objects;

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

    @Column(name = "MaxEcts")
    private int maxEcts;

    @Column(name = "DegreeTypeID")
    private DegreeTypeIDDataModel degreeTypeID;

    @Embedded
    @Column(name = "DepartmentID")
    private DepartmentIDDataModel departmentID;

    @Embedded
    @Column(name = "ProgDirectorID")
    private TeacherIDDataModel programmeDirectorID;


    public ProgrammeDataModel() {}

    public ProgrammeDataModel(ProgrammeIDDataModel progID, String name, String acronym, int quantSemesters, int maxEcts, DegreeTypeIDDataModel degreeTypeID, DepartmentIDDataModel departmentID, TeacherIDDataModel progDirectorID) {

        if (name == null || acronym == null || degreeTypeID == null || departmentID == null || progDirectorID == null || progID == null) {
            throw new IllegalArgumentException("Attributes cannot be null");
        }

        if (maxEcts <= 0 || quantSemesters <= 0) {
            throw new IllegalArgumentException("Attributes must be above 0");
        }

        this.programmeID = progID;
        this.name = name;
        this.acronym = acronym;
        this.quantSemesters = quantSemesters;
        this.maxEcts = maxEcts;
        this.degreeTypeID = degreeTypeID;
        this.departmentID = departmentID;
        this.programmeDirectorID = progDirectorID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeDataModel that = (ProgrammeDataModel) o;
        return Objects.equals(programmeID, that.programmeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmeID);
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

    public int getMaxEcts(){
        return maxEcts;
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