package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.UUID;

public class ProgrammeEditionID implements DomainId {

    private final ProgrammeID _programmeID;
    private final SchoolYearID _schoolYearID;

    public ProgrammeEditionID(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        if (programmeID == null)
            throw new Exception("programmeID cannot be null");
        if (schoolYearID == null)
            throw new Exception("schoolYearID cannot be null");

        _programmeID = programmeID;
        _schoolYearID = schoolYearID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || (!(o instanceof UUID)))
            return false;
        ProgrammeEditionID that = (ProgrammeEditionID) o;
        if (_programmeID.equals(that._programmeID) && _schoolYearID.equals(that._schoolYearID))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "testing toString";
    }
}
