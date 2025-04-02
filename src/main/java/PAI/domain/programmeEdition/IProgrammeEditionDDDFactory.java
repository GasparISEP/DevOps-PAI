package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;

public interface IProgrammeEditionDDDFactory {

    ProgrammeEditionDDD createProgrammeEdition(ProgrammeID programmeID,  SchoolYearID schoolYearID) throws Exception;
}
