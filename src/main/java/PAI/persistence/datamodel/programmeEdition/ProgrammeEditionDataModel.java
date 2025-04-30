package PAI.persistence.datamodel.programmeEdition;

import jakarta.persistence.*;

@Entity
@Table(name = "Programme_Edition")
public class ProgrammeEditionDataModel {

    @EmbeddedId
    private ProgrammeEditionIdDataModel programmeEditionIdDataModel;

    protected ProgrammeEditionDataModel() {}

    public ProgrammeEditionDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel) {
        if(programmeEditionIdDataModel == null) {
            throw new IllegalArgumentException("programmeEditionID cannot be null");
        }
        this.programmeEditionIdDataModel = programmeEditionIdDataModel;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIDDataModel() {
        return programmeEditionIdDataModel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof ProgrammeEditionDataModel)) return false;
        ProgrammeEditionDataModel other = (ProgrammeEditionDataModel) obj;
        return programmeEditionIdDataModel.equals(other.programmeEditionIdDataModel);
    }

    @Override
    public int hashCode() {
        return programmeEditionIdDataModel.hashCode();
    }
}