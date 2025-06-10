package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.ddd.AggregateRoot;

public class ProgrammeEdition implements AggregateRoot<ProgrammeEditionID> {

    private final ProgrammeEditionID programmeEditionID;
    private final ProgrammeID programmeID;
    private final SchoolYearID schoolYearID;
    private final ProgrammeEditionGeneratedID programmeEditionGeneratedID;

    public ProgrammeEdition(ProgrammeEditionID pEID, ProgrammeID pID, SchoolYearID sYID, ProgrammeEditionGeneratedID pEGeneratedID) throws Exception{
        if (pEGeneratedID == null)
            throw new Exception("ProgrammeEditionGeneratedID cannot be null");
        if (pEID == null)
            throw new Exception("ProgrammeEditionID cannot be null");
        if (pID == null)
            throw new Exception("ProgrammeID cannot be null");
        if (sYID == null)
            throw new Exception("SchoolYearID cannot be null");

        programmeEditionID = pEID;
        programmeID = pID;
        schoolYearID = sYID;
        programmeEditionGeneratedID = pEGeneratedID;
    }

    @Override
    public ProgrammeEditionID identity() {
        return programmeEditionID;
    }

    @Override
    public boolean sameAs(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof ProgrammeEdition))
            return false;

        ProgrammeEdition that = (ProgrammeEdition) object;
        if (this.schoolYearID.equals(that.schoolYearID) &&  this.programmeID.equals(that.programmeID))
            return true;
        return false;
    }

    public ProgrammeID findProgrammeIDInProgrammeEdition() {
        return programmeID;
    }

    public SchoolYearID findSchoolYearIDInProgrammeEdition() {
        return schoolYearID;
    }

    public ProgrammeEditionGeneratedID getProgrammeEditionGeneratedGID() {
        return programmeEditionGeneratedID;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof ProgrammeEdition))
            return false;
        ProgrammeEdition that = (ProgrammeEdition) object;
        if (programmeEditionID.equals(that.programmeEditionID))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return programmeEditionID.hashCode();
    }
}