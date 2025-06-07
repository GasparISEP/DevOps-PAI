package PAI.VOs;

import PAI.ddd.DomainId;

public class ProgrammeID implements DomainId {

    private final Acronym _acronym;

    public ProgrammeID(Acronym acronym) {
        if (acronym == null) {
            throw new IllegalArgumentException("Programme acronym must be valid");
        }
        _acronym = acronym;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        ProgrammeID programmeID = (ProgrammeID) object;
        return _acronym.equals(programmeID._acronym);
    }

    @Override
    public int hashCode() {
        return _acronym.hashCode();
    }

    public Acronym getAcronym() {
        return _acronym;
    }

    public String getProgrammeAcronym() {
        return _acronym.toString(); // ou _acronym.getValue()
    }
}
