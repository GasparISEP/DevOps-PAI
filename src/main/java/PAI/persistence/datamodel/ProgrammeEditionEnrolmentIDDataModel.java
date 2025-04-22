package PAI.persistence.datamodel;

import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.Objects;

@Embeddable
public class ProgrammeEditionEnrolmentIDDataModel {

    private String _studentIdDataModel;
    @Embedded
    private ProgrammeEditionIdDataModel _programmeEditionIdDataModel;

    public ProgrammeEditionEnrolmentIDDataModel() {
    }

    public ProgrammeEditionEnrolmentIDDataModel(String studentIdDataModel, ProgrammeEditionIdDataModel programmeEditionIdDataModel) {
        if (studentIdDataModel == null || studentIdDataModel.isBlank()) {
            throw new IllegalArgumentException("studentId cannot be null or blank");
        }
        if (programmeEditionIdDataModel == null) {
            throw new IllegalArgumentException("programmeEditionId cannot be null or blank");
        }
        this._studentIdDataModel = studentIdDataModel;
        this._programmeEditionIdDataModel = programmeEditionIdDataModel;
    }

    public String getStudentIdDataModel() {
        return _studentIdDataModel;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIdDataModel() {
        return _programmeEditionIdDataModel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEditionEnrolmentIDDataModel that = (ProgrammeEditionEnrolmentIDDataModel) o;
        return Objects.equals(_studentIdDataModel, that._studentIdDataModel) && Objects.equals(_programmeEditionIdDataModel, that._programmeEditionIdDataModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentIdDataModel, _programmeEditionIdDataModel);
    }
}


