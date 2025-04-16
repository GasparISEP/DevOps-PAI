package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;

public class ProgrammeEnrolmentID implements DomainId {

    private final StudentID _studentId;
    private final ProgrammeID _programmeId;

    public ProgrammeEnrolmentID (StudentID studentID, ProgrammeID programmeId) {
        _studentId = studentID;
        _programmeId = programmeId;
    }

    public String getProgrammeEnrolmentId() {
        return _studentId.toString() + "-" + _programmeId.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammeEnrolmentID)) return false;
        ProgrammeEnrolmentID that = (ProgrammeEnrolmentID) o;
        return Objects.equals(_studentId, that._studentId) &&
                Objects.equals(_programmeId, that._programmeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentId, _programmeId);
    }

    @Override
    public String toString() {
        return "ProgrammeEnrolmentID{" +
                "studentID=" + _studentId +
                ", programmeID=" + _programmeId +
                '}';
    }
}
