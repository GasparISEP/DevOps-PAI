package PAI.persistence.datamodel.programmeEdition;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

@Embeddable
public class ProgrammeEditionIdDataModel implements Serializable {
    @Embedded
    private ProgrammeIDDataModel _programmeIDDataModel;
    @Embedded
    private SchoolYearIDDataModel _schoolYearIDDataModel;

    public ProgrammeEditionIdDataModel() {}

    public ProgrammeEditionIdDataModel(ProgrammeIDDataModel programmeIDDataModel, SchoolYearIDDataModel schoolYearIDDataModel) {
        if(programmeIDDataModel == null) {
            throw new IllegalArgumentException("ProgrammeIDDataModel cannot be null");
        }
        this._programmeIDDataModel = programmeIDDataModel;

        if(schoolYearIDDataModel == null) {
            throw new IllegalArgumentException("SchoolYearIDDataModel cannot be null");
        }
        this._schoolYearIDDataModel = schoolYearIDDataModel;
    }

    //Getters
    public ProgrammeIDDataModel getProgrammeIdDataModel() {
        return this._programmeIDDataModel;
    }
    public SchoolYearIDDataModel get_schoolYearIDDataModel() {
        return this._schoolYearIDDataModel;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this==objectToCompare) return true;

        if (objectToCompare instanceof ProgrammeEditionIdDataModel oProgrammeEditionIdDataModel) {
            return this._programmeIDDataModel.equals(oProgrammeEditionIdDataModel._programmeIDDataModel) && (this._schoolYearIDDataModel.equals(oProgrammeEditionIdDataModel._schoolYearIDDataModel));
        }
        return false;
    }
}
