package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class ProgrammeEditionID implements DomainId {

    private final String _id;

    ProgrammeEditionID() {
        _id = "1";
    }

    public String getProgrammeEditionID() {
        return "5";
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }
}
