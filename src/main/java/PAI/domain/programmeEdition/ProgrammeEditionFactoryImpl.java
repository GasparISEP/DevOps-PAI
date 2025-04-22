package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;

public class ProgrammeEditionFactoryImpl implements IProgrammeEditionFactory {

    @Override
    public ProgrammeEdition createProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        if (programmeID == null) {
            throw new IllegalArgumentException("Programme ID cannot be null");
        }
        if (schoolYearID == null) {
            throw new IllegalArgumentException("School Year ID cannot be null");
        }
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        return new ProgrammeEdition(programmeEditionID, programmeID, schoolYearID);
    }

    @Override
    public ProgrammeEdition createProgrammeEdition(ProgrammeEditionID programmeEditionID, ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        if (programmeEditionID == null) {
            throw new IllegalArgumentException("Programme Edition ID cannot be null");
        }
        if (programmeID == null) {
            throw new IllegalArgumentException("Programme ID cannot be null");
        }
        if (schoolYearID == null) {
            throw new IllegalArgumentException("School Year ID cannot be null");
        }

        return new ProgrammeEdition(programmeEditionID, programmeID, schoolYearID);
    }
}
