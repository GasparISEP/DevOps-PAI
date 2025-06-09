package PAI.VOs;

import java.util.UUID;

public class ProgrammeEditionGeneratedID {

    private final UUID programmeEditionGID;

    public ProgrammeEditionGeneratedID(UUID programmeEditionID) {
        if (programmeEditionID == null) {
            throw new IllegalArgumentException("ProgrammeEditionGeneratedID cannot be null.");
        }

        this.programmeEditionGID = programmeEditionID;
    }

    public ProgrammeEditionGeneratedID() {
        this.programmeEditionGID = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return programmeEditionGID.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEditionGeneratedID that = (ProgrammeEditionGeneratedID) o;
        return programmeEditionGID.equals(that.programmeEditionGID);
    }

    @Override
    public int hashCode() {
        return programmeEditionGID.hashCode();
    }
}
