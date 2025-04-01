package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;

public interface IProgrammeEditionDDDFactory {

    ProgrammeEditionDDD createProgrammeEdition(ProgrammeEditionID programmeEditionID, ProgrammeID programmeID,  SchoolYearID schoolYearID);
}
