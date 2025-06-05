package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.UUID;

public class ProgrammeEditionEnrolmentGeneratedID {
    private final UUID _programmeEditionEnrolmentID;


    public ProgrammeEditionEnrolmentGeneratedID() {
        this._programmeEditionEnrolmentID = UUID.randomUUID();
    }

    public ProgrammeEditionEnrolmentGeneratedID(UUID programmeEditionEnrolmentID) {
        this._programmeEditionEnrolmentID = programmeEditionEnrolmentID;
    }

    @Override
    public String toString() {
        return this._programmeEditionEnrolmentID.toString();
    }

    public UUID toUUID() {
        return _programmeEditionEnrolmentID;
    }
}
