package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.ddd.IRepository;
import PAI.domain.programmeEdition.ProgrammeEditionDDD;

import java.util.Optional;

public interface IProgrammeEditionRepositoryDDD extends IRepository<ProgrammeEditionID, ProgrammeEditionDDD> {

    public boolean createProgrammeEdition(ProgrammeID programmeid, SchoolYearID schoolYearid);

    public Optional <ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid);
}
