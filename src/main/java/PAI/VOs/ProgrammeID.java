package PAI.VOs;

import PAI.ddd.DomainId;

public class ProgrammeID implements DomainId {

    private final NameWithNumbersAndSpecialChars _name;
    private final Acronym _acronym;

    public ProgrammeID(NameWithNumbersAndSpecialChars name, Acronym acronym) {
        if (name == null) {
            throw new IllegalArgumentException("Programme name must be valid");
        }
        _name = name;

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
        return _name.equals(programmeID._name)
                && _acronym.equals(programmeID._acronym);
    }

    @Override
    public int hashCode() {
        return _name.hashCode() + _acronym.hashCode();
    }

    public NameWithNumbersAndSpecialChars getName() {
        return _name;
    }

    public Acronym getAcronym() {
        return _acronym;
    }
}
