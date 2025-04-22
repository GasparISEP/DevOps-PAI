package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;

public class ProgrammeEditionFactoryImpl implements IProgrammeEditionFactory {

    @Override
    public ProgrammeEdition createProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        return new ProgrammeEdition(programmeEditionID, programmeID, schoolYearID);
    }

    @Override
    public ProgrammeEdition createProgrammeEdition(ProgrammeEditionID programmeEditionID, ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        return new ProgrammeEdition(programmeEditionID, programmeID, schoolYearID);
    }
}
