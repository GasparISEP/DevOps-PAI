package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class ProgrammeEditionID implements DomainId {

    private final String _id;

    ProgrammeEditionID() {
        _id = "1";
    }
}
