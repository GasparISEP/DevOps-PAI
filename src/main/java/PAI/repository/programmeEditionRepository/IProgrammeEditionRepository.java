package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.ddd.IRepository;
import PAI.domain.programmeEdition.ProgrammeEdition;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionRepository extends IRepository<ProgrammeEditionID, ProgrammeEdition> {

    public boolean createProgrammeEdition(ProgrammeID programmeid, SchoolYearID schoolYearid);

    public Optional <ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid);

    Optional<ProgrammeEdition> ofIdentity(ProgrammeEditionID id);

    List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeid);

    SchoolYearID getSchoolYearIDByProgrammeEdition (ProgrammeEdition programmeEdition);
}
