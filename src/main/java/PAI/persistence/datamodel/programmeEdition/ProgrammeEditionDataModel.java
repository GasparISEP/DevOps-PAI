package PAI.persistence.datamodel.programmeEdition;

import jakarta.persistence.*;

import static PAI.utils.ValidationUtils.validateNotNull;

@Entity
@Table(name = "Programme_Edition")
public class ProgrammeEditionDataModel {

    @EmbeddedId
    private ProgrammeEditionIdDataModel programmeEditionIdDataModel;

    @Embedded
    private ProgrammeEditionGeneratedIDDataModel programmeEditionGeneratedIDDataModel;

    protected ProgrammeEditionDataModel() {}

    public ProgrammeEditionDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel) {
        if(programmeEditionIdDataModel == null) {
            throw new IllegalArgumentException("programmeEditionID cannot be null");
        }
        this.programmeEditionIdDataModel = programmeEditionIdDataModel;
    }

    public ProgrammeEditionDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel,
                                     ProgrammeEditionGeneratedIDDataModel programmeEditionGeneratedIDDataModel) {

        this.programmeEditionIdDataModel = validateNotNull(programmeEditionIdDataModel,"ProgrammeEditionIdDataModel");
        this.programmeEditionGeneratedIDDataModel = validateNotNull(programmeEditionGeneratedIDDataModel,"ProgrammeEditionGeneratedIDDataModel");
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIDDataModel() {
        return programmeEditionIdDataModel;
    }

    public ProgrammeEditionGeneratedIDDataModel getProgrammeEditionGeneratedIDDataModel() {
        return programmeEditionGeneratedIDDataModel;
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