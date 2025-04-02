package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.ddd.IRepository;
import PAI.domain.ProgrammeEdition;
import PAI.domain.programmeEdition.ProgrammeEditionDDD;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionRepositoryDDD extends IRepository<ProgrammeEditionID, ProgrammeEditionDDD> {

    public boolean createProgrammeEdition(ProgrammeID programmeid, SchoolYearID schoolYearid);

    public Optional <ProgrammeEditionDDD> findProgrammeEditionByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid);

    public List<ProgrammeEditionDDD> getAllProgrammeEditions();

    public ProgrammeEditionDDD findProgrammeEditionByID(ProgrammeEditionID programmeEditionID);
}
