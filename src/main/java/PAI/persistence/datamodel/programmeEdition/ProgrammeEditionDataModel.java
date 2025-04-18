package PAI.persistence.datamodel.programmeEdition;

import PAI.persistence.datamodel.ProgrammeIDDataModel;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "programmeEdition")
public class ProgrammeEditionDataModel {

    @EmbeddedId
    private ProgrammeEditionIdDataModel _programmeEditionID;
    @EmbeddedId
    private ProgrammeIDDataModel _programmeID;
    private UUID _schoolYearID;

    protected ProgrammeEditionDataModel() {}

    public ProgrammeEditionDataModel(ProgrammeEditionIdDataModel programmeEditionID, ProgrammeIDDataModel programmeID, UUID schoolYearID) {

        this._programmeEditionID = programmeEditionID;
        this._programmeID = programmeID;
        this._schoolYearID = schoolYearID;
    }
}