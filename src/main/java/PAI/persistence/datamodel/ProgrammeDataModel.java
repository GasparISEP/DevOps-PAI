package PAI.persistence.datamodel;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.mapper.ProgrammeIDMapper;
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
        ProgrammeIDMapper mapper = new ProgrammeIDMapper();

        _programmeID = mapper.toData(id);

        _name = programme.getProgrammeName().getnameWithNumbersAndSpecialChars();
        _acronym = programme.getAcronym().getAcronym();
        _quantSemesters = programme.getQuantSemesters().getQuantityOfSemesters();
        _quantEcts = programme.getQuantEcts().getQuantEcts();
        _degreeTypeID = programme.getDegreeTypeID().getDTID();
        _departmentID = programme.getDepartment().getAcronym().getAcronym();
        _programmeDirectorID = programme.getProgrammeDirectorID().getTeacherAcronym().getAcronym();
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