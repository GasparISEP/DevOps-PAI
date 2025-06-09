package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import org.springframework.stereotype.Component;

@Component
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
        ProgrammeEditionGeneratedID programmeEditionGeneratedID = new ProgrammeEditionGeneratedID();
        return new ProgrammeEdition(programmeEditionID, programmeID, schoolYearID, programmeEditionGeneratedID);
    }

    @Override
    public ProgrammeEdition createProgrammeEdition(ProgrammeEditionID programmeEditionID, ProgrammeID programmeID,
                                                   SchoolYearID schoolYearID,
                                                   ProgrammeEditionGeneratedID programmeEditionGeneratedID) throws Exception {
        if (programmeEditionID == null) {
            throw new IllegalArgumentException("Programme Edition ID cannot be null");
        }
        if (programmeID == null) {
            throw new IllegalArgumentException("Programme ID cannot be null");
        }
        if (schoolYearID == null) {
            throw new IllegalArgumentException("School Year ID cannot be null");
        }

        if (programmeEditionGeneratedID == null) {
            throw new IllegalArgumentException("Programme Edition Generated ID cannot be null");
        }

        return new ProgrammeEdition(programmeEditionID, programmeID, schoolYearID, programmeEditionGeneratedID);
    }
}
