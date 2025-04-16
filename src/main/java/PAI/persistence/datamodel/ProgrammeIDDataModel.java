package PAI.persistence.datamodel;

import PAI.VOs.ProgrammeID;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProgrammeIDDataModel {

    private String _name;
    private String _acronym;


    public ProgrammeIDDataModel() {
    }


    public ProgrammeIDDataModel(ProgrammeID programmeID) {
        _name = programmeID.getName().getnameWithNumbersAndSpecialChars();
        _acronym = programmeID.getAcronym().getAcronym();
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof ProgrammeIDDataModel)) return false;
        ProgrammeIDDataModel programmeIDDataModel = (ProgrammeIDDataModel) objectToCompare;
        return _acronym.equals(programmeIDDataModel._acronym) &&
                _name.equals(programmeIDDataModel._name);
    }

    @Override
    public int hashCode() {
        return _acronym.hashCode() + _name.hashCode();
    }

    public String getName() {
        return _name;
    }

    public String getAcronym() {
        return _acronym;
    }

}
