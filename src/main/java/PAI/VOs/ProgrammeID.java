package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.UUID;

public class ProgrammeID implements ValueObject {

    private final String _progID;

    public ProgrammeID() {
        _progID = UUID.randomUUID().toString();
    }
}
