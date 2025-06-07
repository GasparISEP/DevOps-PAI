package PAI.persistence.datamodel.programme;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProgrammeIDDataModel implements Serializable {

    private String programmeAcronym;


    public ProgrammeIDDataModel() {
    }

    public ProgrammeIDDataModel(String acronym) {
        if (acronym == null) {
            throw new IllegalArgumentException("Attributes cannot be null");
        }
        this.programmeAcronym = acronym;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof ProgrammeIDDataModel)) return false;
        ProgrammeIDDataModel programmeIDDataModel = (ProgrammeIDDataModel) objectToCompare;
        return programmeAcronym.equals(programmeIDDataModel.programmeAcronym);
    }

    @Override
    public int hashCode() {return Objects.hash(programmeAcronym);}

    public String getAcronym() {
        return programmeAcronym;
    }

}
