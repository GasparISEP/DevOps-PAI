package PAI.persistence.datamodel.programmeEdition;

import PAI.persistence.datamodel.ProgrammeIDDataModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "programmeEdition")
public class ProgrammeEditionDataModel {

    @EmbeddedId
    private ProgrammeEditionIdDataModel _programmeEditionIdDataModel;
    @Embedded
    private ProgrammeIDDataModel _programmeIDDataModel;
    private UUID _schoolYearID;

    protected ProgrammeEditionDataModel() {}

    public ProgrammeEditionDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel, ProgrammeIDDataModel programmeIDDataModel, UUID schoolYearID) {
        if(programmeEditionIdDataModel == null) {
            throw new IllegalArgumentException("programmeEditionID cannot be null");
        }
        if(programmeIDDataModel == null) {
            throw new IllegalArgumentException("programmeID cannot be null");
        }
        if(schoolYearID == null) {
            throw new IllegalArgumentException("schoolYearID cannot be null");
        }
        this._programmeEditionIdDataModel = programmeEditionIdDataModel;
        this._programmeIDDataModel = programmeIDDataModel;
        this._schoolYearID = schoolYearID;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIDDataModel() {
        return _programmeEditionIdDataModel;
    }

    public ProgrammeIDDataModel getProgrammeIDDataModel() {
        return _programmeIDDataModel;
    }

    public UUID getSchoolYearID() {
        return _schoolYearID;
    }

}