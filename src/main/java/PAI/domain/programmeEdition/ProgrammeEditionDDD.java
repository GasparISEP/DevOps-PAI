package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.ddd.AggregateRoot;

public class ProgrammeEditionDDD implements AggregateRoot<ProgrammeEditionID> {

    private final ProgrammeEditionID _programmeEditionID;
    private final ProgrammeID _programmeID;
    private final SchoolYearID _schoolYearID;

    public ProgrammeEditionDDD(ProgrammeEditionID pEID, ProgrammeID pID, SchoolYearID sYID) throws Exception{

        if (pEID == null)
            throw new Exception("ProgrammeEditionID cannot be null");
        if (pID == null)
            throw new Exception("ProgrammeID cannot be null");
        if (sYID == null)
            throw new Exception("SchoolYearID cannot be null");

        _programmeEditionID = pEID;
        _programmeID = pID;
        _schoolYearID = sYID;
    }

    @Override
    public ProgrammeEditionID identity() {
        return _programmeEditionID;
    }

    @Override
    public boolean sameAs(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof ProgrammeEditionDDD))
            return false;

        ProgrammeEditionDDD that = (ProgrammeEditionDDD) object;
        if (this._schoolYearID.equals(that._schoolYearID) &&  this._programmeID.equals(that._programmeID))
            return true;
        return false;
    }

    public ProgrammeID findProgrammeIDInProgrammeEdition() {
        return _programmeID;
    }

    public SchoolYearID findSchoolYearIDInProgrammeEdition() {
        return _schoolYearID;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof ProgrammeEditionDDD))
            return false;
        ProgrammeEditionDDD that = (ProgrammeEditionDDD) object;
        if (_programmeEditionID.equals(that._programmeEditionID))
            return true;
        return false;
    }
}