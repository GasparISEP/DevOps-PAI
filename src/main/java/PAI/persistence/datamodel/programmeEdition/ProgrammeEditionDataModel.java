package PAI.persistence.datamodel.programmeEdition;

import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import jakarta.persistence.*;

@Entity
@Table(name = "Programme_Edition")
public class ProgrammeEditionDataModel {

    @EmbeddedId
    private ProgrammeEditionIdDataModel _programmeEditionIdDataModel;

    @Embedded
    private ProgrammeIDDataModel _programmeIDDataModel;

    @Embedded
    private SchoolYearIDDataModel _schoolYearIDDataModel;

    protected ProgrammeEditionDataModel() {}

    public ProgrammeEditionDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel, ProgrammeIDDataModel programmeIDDataModel,
                                     SchoolYearIDDataModel schoolYearIDDatamodel) {
        if(programmeEditionIdDataModel == null) {
            throw new IllegalArgumentException("programmeEditionID cannot be null");
        }
        if(programmeIDDataModel == null) {
            throw new IllegalArgumentException("programmeID cannot be null");
        }
        if(schoolYearIDDatamodel == null) {
            throw new IllegalArgumentException("schoolYearID cannot be null");
        }
        this._programmeEditionIdDataModel = programmeEditionIdDataModel;
        this._programmeIDDataModel = programmeIDDataModel;
        this._schoolYearIDDataModel = schoolYearIDDatamodel;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIDDataModel() {
        return _programmeEditionIdDataModel;
    }

    public ProgrammeIDDataModel getProgrammeIDDataModel() {
        return _programmeIDDataModel;
    }

    public SchoolYearIDDataModel getSchoolYearIDDataModel() {
        return _schoolYearIDDataModel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof ProgrammeEditionDataModel)) return false;
        ProgrammeEditionDataModel other = (ProgrammeEditionDataModel) obj;
        return _programmeEditionIdDataModel.equals(other._programmeEditionIdDataModel) &&
                _programmeIDDataModel.equals(other._programmeIDDataModel) &&
                _schoolYearIDDataModel.equals(other._schoolYearIDDataModel);
    }

    @Override
    public int hashCode() {
        return _programmeEditionIdDataModel.hashCode() + _programmeIDDataModel.hashCode() + _schoolYearIDDataModel.hashCode();
    }
}