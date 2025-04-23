package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;

public class ProgrammeEditionEnrolmentID implements DomainId {

    private final ProgrammeEditionID _programmeEditionId;
    private final StudentID _studentiD;

    public ProgrammeEditionEnrolmentID(ProgrammeEditionID programmeEditionId, StudentID studentiD) {

        validateProgrammeEditionID(programmeEditionId);
        validateStudentID(studentiD);

        this._programmeEditionId = programmeEditionId;
        this._studentiD = studentiD;
    }

    private void validateProgrammeEditionID (ProgrammeEditionID programmeEditioniD) {
        if (programmeEditioniD == null)
            throw new IllegalArgumentException ("ProgrammeEdition must be valid");
    }

    private void validateStudentID (StudentID studentId) {
        if (studentId == null)
            throw new IllegalArgumentException ("Student must be valid");
    }

    public ProgrammeEditionID getProgrammeEditionId() {
        return _programmeEditionId;
    }

    public StudentID getStudentiD() {
        return _studentiD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEditionEnrolmentID that = (ProgrammeEditionEnrolmentID) o;
        return Objects.equals(_programmeEditionId, that._programmeEditionId) && Objects.equals(_studentiD, that._studentiD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_programmeEditionId, _studentiD);
    }

    @Override
    public String toString() {
        return "ProgrammeEditionEnrolmentID{" +
                "_programmeEditionId=" + _programmeEditionId +
                ", _studentiD=" + _studentiD +
                '}';
    }
}
