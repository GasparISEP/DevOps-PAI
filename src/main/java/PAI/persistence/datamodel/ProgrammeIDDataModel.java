package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProgrammeIDDataModel implements Serializable {

    private String _programmeName;
    private String _programmeAcronym;


    public ProgrammeIDDataModel() {
    }

    public ProgrammeIDDataModel(String name, String acronym) {
        _programmeName = name;
        _programmeAcronym = acronym;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof ProgrammeIDDataModel)) return false;
        ProgrammeIDDataModel programmeIDDataModel = (ProgrammeIDDataModel) objectToCompare;
        return _programmeAcronym.equals(programmeIDDataModel._programmeAcronym) &&
                _programmeName.equals(programmeIDDataModel._programmeName);
    }

    @Override
    public int hashCode() {
        return _programmeAcronym.hashCode() + _programmeName.hashCode();
    }

    public String getName() {
        return _programmeName;
    }

    public String getAcronym() {
        return _programmeAcronym;
    }

}
