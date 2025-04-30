package PAI.persistence.datamodel.programme;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProgrammeIDDataModel implements Serializable {

    private String programmeName;
    private String programmeAcronym;


    public ProgrammeIDDataModel() {
    }

    public ProgrammeIDDataModel(String name, String acronym) {
        if (name == null | acronym == null) {
            throw new IllegalArgumentException("Attributes cannot be null");
        }
        this.programmeName = name;
        this.programmeAcronym = acronym;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof ProgrammeIDDataModel)) return false;
        ProgrammeIDDataModel programmeIDDataModel = (ProgrammeIDDataModel) objectToCompare;
        return programmeAcronym.equals(programmeIDDataModel.programmeAcronym) &&
                programmeName.equals(programmeIDDataModel.programmeName);
    }

    @Override
    public int hashCode() {return Objects.hash(programmeAcronym, programmeName);}

    public String getName() {
        return programmeName;
    }

    public String getAcronym() {
        return programmeAcronym;
    }

}
