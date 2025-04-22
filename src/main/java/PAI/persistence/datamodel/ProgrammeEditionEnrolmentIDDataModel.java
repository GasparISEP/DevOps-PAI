package PAI.persistence.datamodel;

import PAI.VOs.ProgrammeEditionID;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.Objects;

@Embeddable
public class ProgrammeEditionEnrolmentIDDataModel {

    private String _studentId;
    @Embedded
    private ProgrammeEditionID _programmeEditionId;

    public ProgrammeEditionEnrolmentIDDataModel() {
    }

    public ProgrammeEditionEnrolmentIDDataModel(String studentId, ProgrammeEditionID programmeEditionId) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("studentId cannot be null or blank");
        }
        if (programmeEditionId == null) {
            throw new IllegalArgumentException("programmeEditionId cannot be null or blank");
        }
        this._studentId = studentId;
        this._programmeEditionId = programmeEditionId;
    }

    public String getStudentId() {
        return _studentId;
    }

    public ProgrammeEditionID getProgrammeEditionId() {
        return _programmeEditionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEditionEnrolmentIDDataModel that = (ProgrammeEditionEnrolmentIDDataModel) o;
        return Objects.equals(_studentId, that._studentId) && Objects.equals(_programmeEditionId, that._programmeEditionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentId, _programmeEditionId);
    }
}


