package PAI.VOs;

import PAI.ddd.ValueObject;
import java.util.Objects;
import java.util.UUID;

public class ProgrammeEnrolmentGeneratedID implements ValueObject {

    private final UUID programmeEnrolmentGID ;

    public ProgrammeEnrolmentGeneratedID(UUID programmeEnrolmentGID) {
        if (programmeEnrolmentGID == null) {
            throw new IllegalArgumentException("ProgrammeEnrolmentGeneratedID cannot be null.");
        }
        this.programmeEnrolmentGID = programmeEnrolmentGID;
    }

    public ProgrammeEnrolmentGeneratedID () {
        this.programmeEnrolmentGID= UUID.randomUUID();
    }

    public UUID getProgrammeEnrolmentGID() {
        return programmeEnrolmentGID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEnrolmentGeneratedID that = (ProgrammeEnrolmentGeneratedID) o;
        return programmeEnrolmentGID.equals(that.programmeEnrolmentGID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmeEnrolmentGID);
    }

    @Override
    public String toString() {
        return programmeEnrolmentGID.toString();
    }
}
