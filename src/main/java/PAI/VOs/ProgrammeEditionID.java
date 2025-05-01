package PAI.VOs;

import PAI.ddd.DomainId;

public class ProgrammeEditionID implements DomainId {

    private final ProgrammeID programmeID;
    private final SchoolYearID schoolYearID;

    public ProgrammeEditionID(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        if (programmeID == null)
            throw new Exception("programmeID cannot be null");
        if (schoolYearID == null)
            throw new Exception("schoolYearID cannot be null");

        this.programmeID = programmeID;
        this.schoolYearID = schoolYearID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || (!(o instanceof ProgrammeEditionID)))
            return false;
        ProgrammeEditionID that = (ProgrammeEditionID) o;
        if (programmeID.equals(that.programmeID) && schoolYearID.equals(that.schoolYearID))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return programmeID.toString()+"-"+ schoolYearID.getSchoolYearID().toString();
    }

    @Override
    public int hashCode() {
        return programmeID.hashCode() + schoolYearID.hashCode();
    }

    public boolean isSameProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID){
        return this.programmeID.equals(programmeID) && this.schoolYearID.equals(schoolYearID);
    }

    public ProgrammeID getProgrammeID() {
        return programmeID;
    }

    public SchoolYearID getSchoolYearID() {
        return schoolYearID;
    }
}
