package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProgrammeEditionEnrolmentIDDataModel {

    private String _studentId;
    private String _programmeEditionId;

    public ProgrammeEditionEnrolmentIDDataModel() {
    }

    public ProgrammeEditionEnrolmentIDDataModel(String studentId, String programmeEditionId) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("studentId cannot be null or blank");
        }
        if (programmeEditionId == null || programmeEditionId.isBlank()) {
            throw new IllegalArgumentException("programmeEditionId cannot be null or blank");
        }
        this._studentId = studentId;
        this._programmeEditionId = programmeEditionId;
    }

    public String getStudentId() {
        return _studentId;
    }

    public String getProgrammeEditionId() {
        return _programmeEditionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammeEditionEnrolmentIDDataModel)) return false;
        ProgrammeEditionEnrolmentIDDataModel that = (ProgrammeEditionEnrolmentIDDataModel) o;
        return Objects.equals(_studentId, that._studentId) &&
                Objects.equals(_programmeEditionId, that._programmeEditionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentId, _programmeEditionId);
    }
}
