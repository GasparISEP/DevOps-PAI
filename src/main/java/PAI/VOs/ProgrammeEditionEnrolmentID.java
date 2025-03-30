package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class ProgrammeEditionEnrolmentID implements DomainId {

    private final UUID _programmeEditionEnrolmentId;

    public ProgrammeEditionEnrolmentID() {
        this._programmeEditionEnrolmentId = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (objectToCompare == null || getClass() != objectToCompare.getClass()) return false;
        ProgrammeEditionEnrolmentID that = (ProgrammeEditionEnrolmentID) objectToCompare;
        return Objects.equals(_programmeEditionEnrolmentId, that._programmeEditionEnrolmentId);
    }

    @Override
    public String toString() {
        return this._programmeEditionEnrolmentId.toString();
    }
}
