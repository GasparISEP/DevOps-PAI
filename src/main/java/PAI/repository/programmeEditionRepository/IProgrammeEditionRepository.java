package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.ddd.IRepository;
import PAI.domain.programmeEdition.ProgrammeEdition;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionRepository extends IRepository<ProgrammeEditionID, ProgrammeEdition> {

    ProgrammeEdition save(ProgrammeEdition programmeEdition);

    Iterable<ProgrammeEdition> findAll();

    Optional<ProgrammeEdition> ofIdentity(ProgrammeEditionID id);

    boolean containsOfIdentity(ProgrammeEditionID id);

    public Optional <ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) throws Exception;

    List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeid);

    SchoolYearID getSchoolYearIDByProgrammeEdition (ProgrammeEdition programmeEdition);
}
