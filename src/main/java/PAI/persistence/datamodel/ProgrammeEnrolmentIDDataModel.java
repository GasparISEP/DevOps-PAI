package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProgrammeEnrolmentIDDataModel implements Serializable {

    private StudentIDDataModel _studentID;
    private ProgrammeIDDataModel _programmeID;

    public ProgrammeEnrolmentIDDataModel () {}

    public ProgrammeEnrolmentIDDataModel (StudentIDDataModel studentID, ProgrammeIDDataModel programmeID) {
        _studentID = studentID;
        _programmeID = programmeID;
    }

    public StudentIDDataModel getStudentID () {
        return _studentID;
    }

    public ProgrammeIDDataModel getProgrammeID () {
        return _programmeID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof ProgrammeEnrolmentIDDataModel)) return false;

        ProgrammeEnrolmentIDDataModel other = (ProgrammeEnrolmentIDDataModel) obj;

        return Objects.equals(this._studentID, other._studentID) &&
                Objects.equals(this._programmeID, other._programmeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentID, _programmeID);
    }
}
