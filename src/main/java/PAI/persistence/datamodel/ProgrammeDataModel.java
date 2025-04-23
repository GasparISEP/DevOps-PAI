package PAI.persistence.datamodel;

import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import jakarta.persistence.*;

@Entity
@Table(name = "Programme")
public class ProgrammeDataModel {

    @EmbeddedId
    private ProgrammeIDDataModel _programmeID;

    @Column(name = "Name")
    private String _name;

    @Column(name = "Acronym")
    private String _acronym;

    @Column(name = "QuantSemesters")
    private int _quantSemesters;

    @Column(name = "QuantEcts")
    private int _quantEcts;

    @Column(name = "DegreeTypeID")
    private String _degreeTypeID;

    @Embedded
    @Column(name = "DepartmentID")
    private DepartmentIDDataModel _departmentID;

    @Embedded
    @Column(name = "ProgDirectorID")
    private TeacherIDDataModel _programmeDirectorID;

    public ProgrammeDataModel() {}

    public ProgrammeDataModel(ProgrammeIDDataModel progID, String name, String acronym, int quantSemesters, int quantEcts, String degreeTypeID, DepartmentIDDataModel departmentID, TeacherIDDataModel progDirectorID) {

        _programmeID = progID;
        _name = name;
        _acronym = acronym;
        _quantSemesters = quantSemesters;
        _quantEcts = quantEcts;
        _degreeTypeID = degreeTypeID;
        _departmentID = departmentID;
        _programmeDirectorID = progDirectorID;
    }

    public String getName(){
        return _name;
    }

    public String getAcronym(){
        return _acronym;
    }

    public int getQuantSemesters(){
        return _quantSemesters;
    }

    public int getQuantEcts(){
        return _quantEcts;
    }

    public String getDegreeTypeID(){
        return _degreeTypeID;
    }

    public DepartmentIDDataModel getDepartmentID(){
        return _departmentID;
    }

    public TeacherIDDataModel getProgrammeDirectorID(){
        return _programmeDirectorID;
    }

    public ProgrammeIDDataModel getProgID() {
        return _programmeID;
    }
}