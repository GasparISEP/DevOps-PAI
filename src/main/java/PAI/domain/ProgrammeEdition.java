package PAI.domain;

public class ProgrammeEdition {

    private Programme _programme;
    private SchoolYear _schoolYear;

    public ProgrammeEdition(Programme programme, SchoolYear schoolYear) {

        if (programme == null)
            throw new IllegalArgumentException("Programme cannot be null");
        if (schoolYear == null)
            throw new IllegalArgumentException("School year cannot be null");

        _programme = programme;
        _schoolYear = schoolYear;
    }

    @Override
    public boolean equals (Object other) {

        if (!(other instanceof ProgrammeEdition))
            return false;

        ProgrammeEdition otherEdition = (ProgrammeEdition) other;
        if (otherEdition._programme.equals(_programme) && otherEdition._schoolYear.isSameSchoolYear(_schoolYear))
            return true;
        return false;
    }

    //US17
    public Programme findProgrammeInProgrammeEdition() {
        return _programme;
    }

    //US17
    public SchoolYear findSchoolYearInProgrammeEdition() {
        return _schoolYear;
    }
}
