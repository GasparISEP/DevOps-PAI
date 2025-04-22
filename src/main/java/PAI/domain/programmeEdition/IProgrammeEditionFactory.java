package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;

public interface IProgrammeEditionFactory {

    ProgrammeEdition createProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception;

    ProgrammeEdition createProgrammeEdition(ProgrammeEditionID programmeEditionID, ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception;
}
