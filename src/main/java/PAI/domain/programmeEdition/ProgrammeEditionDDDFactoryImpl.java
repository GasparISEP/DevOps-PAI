package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;

public class ProgrammeEditionDDDFactoryImpl implements IProgrammeEditionDDDFactory {

    @Override
    public ProgrammeEditionDDD createProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        return new ProgrammeEditionDDD(programmeEditionID, programmeID, schoolYearID);
    }
}
