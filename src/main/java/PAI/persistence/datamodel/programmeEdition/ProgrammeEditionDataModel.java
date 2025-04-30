package PAI.persistence.datamodel.programmeEdition;

import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import jakarta.persistence.*;

@Entity
@Table(name = "Programme_Edition")
public class ProgrammeEditionDataModel {

    @EmbeddedId
    private ProgrammeEditionIdDataModel _programmeEditionIdDataModel;

    protected ProgrammeEditionDataModel() {}

    public ProgrammeEditionDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel) {
        if(programmeEditionIdDataModel == null) {
            throw new IllegalArgumentException("programmeEditionID cannot be null");
        }
        this._programmeEditionIdDataModel = programmeEditionIdDataModel;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIDDataModel() {
        return _programmeEditionIdDataModel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof ProgrammeEditionDataModel)) return false;
        ProgrammeEditionDataModel other = (ProgrammeEditionDataModel) obj;
        return _programmeEditionIdDataModel.equals(other._programmeEditionIdDataModel);
    }

    @Override
    public int hashCode() {
        return _programmeEditionIdDataModel.hashCode();
    }
}