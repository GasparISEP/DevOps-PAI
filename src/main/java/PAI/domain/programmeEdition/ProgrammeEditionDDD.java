package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.ddd.AggregateRoot;
import PAI.domain.ProgrammeEdition;

public class ProgrammeEditionDDD implements AggregateRoot<ProgrammeEditionID> {

    private final ProgrammeEditionID _programmeEditionID;
    private final ProgrammeID _programmeID;
    private final SchoolYearID _schoolYear;

    public ProgrammeEditionDDD(ProgrammeEditionID pEID, ProgrammeID pID, SchoolYearID sYID) throws Exception{

        if (pEID == null)
            throw new Exception("ProgrammeEditionID cannot be null");
        if (pID == null)
            throw new Exception("ProgrammeID cannot be null");
        if (sYID == null)
            throw new Exception("SchoolYearID cannot be null");

        _programmeEditionID = pEID;
        _programmeID = pID;
        _schoolYear = sYID;
    }

    @Override
    public ProgrammeEditionID identity() {
        return _programmeEditionID;
    }

    @Override
    public boolean sameAs(Object object) {
        return false;
    }
}
