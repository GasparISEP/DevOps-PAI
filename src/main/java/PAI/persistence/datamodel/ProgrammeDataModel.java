package PAI.persistence.datamodel;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import jakarta.persistence.*;

@Entity
@Table(name = "Programme")
public class ProgrammeDataModel {

    @EmbeddedId
    private ProgrammeIDDataModel _programmeID;

    private String _name;
    private String _acronym;
    private int _quantSemesters;
    private int _quantEcts;
    private String _degreeTypeID;
    private String _departmentID;
    private String _programmeDirectorID;

    public ProgrammeDataModel() {}

    public ProgrammeDataModel(Programme programme) {

        ProgrammeID id = programme.getProgrammeID();
        _programmeID = new ProgrammeIDDataModel(id);

        _name = programme.getProgrammeName().toString();
        _acronym = programme.getAcronym().toString();
        _quantSemesters = programme.getQuantSemesters().getQuantityOfSemesters();
        _quantEcts = programme.getQuantEcts().getQuantEcts();
        _degreeTypeID = programme.getDegreeTypeID().toString();
        _departmentID = programme.getDepartment().toString();
        _programmeDirectorID = programme.getProgrammeDirectorID().toString();
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

    public String getDepartmentID(){
        return _departmentID;
    }

    public String getProgrammeDirectorID(){
        return _programmeDirectorID;
    }

}