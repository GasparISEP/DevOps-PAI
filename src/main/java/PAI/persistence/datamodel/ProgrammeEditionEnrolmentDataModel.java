package PAI.persistence.datamodel;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "ProgrammeEditionEnrolments")
public class ProgrammeEditionEnrolmentDataModel {

    @EmbeddedId
    private ProgrammeEditionEnrolmentIDDataModel _programmeEditionEnrolmentIDDataModel;

    public ProgrammeEditionEnrolmentDataModel() {
    }

    public ProgrammeEditionEnrolmentDataModel(ProgrammeEditionEnrolmentIDDataModel programmeEditionEnrolmentIDDataModel) {
        this._programmeEditionEnrolmentIDDataModel = programmeEditionEnrolmentIDDataModel;
    }

    public ProgrammeEditionEnrolmentIDDataModel getProgrammeEditionEnrolmentIDDataModel() {
        return _programmeEditionEnrolmentIDDataModel;
    }

    public void setProgrammeEditionEnrolmentIDDataModel(ProgrammeEditionEnrolmentIDDataModel programmeEditionEnrolmentIDDataModel) {
        this._programmeEditionEnrolmentIDDataModel = programmeEditionEnrolmentIDDataModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammeEditionEnrolmentDataModel)) return false;
        ProgrammeEditionEnrolmentDataModel that = (ProgrammeEditionEnrolmentDataModel) o;
        return Objects.equals(_programmeEditionEnrolmentIDDataModel, that._programmeEditionEnrolmentIDDataModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_programmeEditionEnrolmentIDDataModel);
    }
}
