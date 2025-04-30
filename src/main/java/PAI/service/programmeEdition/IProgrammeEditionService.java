package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionService {
    ProgrammeEdition createProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception;
    Optional<ProgrammeEdition> saveProgrammeEdition(ProgrammeEdition programmeEdition) throws Exception;
    List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID) throws Exception;
}
