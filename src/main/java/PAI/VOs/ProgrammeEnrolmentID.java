package PAI.VOs;

import java.util.Objects;
import java.util.UUID;

public class ProgrammeEnrolmentID {

    private final UUID _programmeEnrolmentId;

    public ProgrammeEnrolmentID() {
        this._programmeEnrolmentId = UUID.randomUUID();
    }

    public UUID getProgrammeEnrolmentId() {
        return _programmeEnrolmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEnrolmentID that = (ProgrammeEnrolmentID) o;
        return _programmeEnrolmentId.equals(that._programmeEnrolmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_programmeEnrolmentId);
    }

    @Override
    public String toString() {
        return _programmeEnrolmentId.toString();
    }
}
