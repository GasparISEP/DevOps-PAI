package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.UUID;

public class ProgrammeEditionID implements DomainId {

    private final ProgrammeID _programmeID;
    private final SchoolYearID _schoolYearID;

    public ProgrammeEditionID(ProgrammeID programmeID, SchoolYearID schoolYearID) {
        _programmeID = programmeID;
        _schoolYearID = schoolYearID;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public String toString() {
        return "testing toString";
    }
}
