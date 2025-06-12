package PAI.domain.repositoryInterfaces.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.ddd.IRepository;
import PAI.domain.programmeEdition.ProgrammeEdition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionRepository extends IRepository<ProgrammeEditionID, ProgrammeEdition> {

    ProgrammeEdition save(ProgrammeEdition programmeEdition);

    Iterable<ProgrammeEdition> findAll();

    Optional<ProgrammeEdition> ofIdentity(ProgrammeEditionID id);

    boolean containsOfIdentity(ProgrammeEditionID id);

    Optional <ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) throws Exception;

    List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeid);

    List<ProgrammeEdition> findByProgrammeIDs(List<ProgrammeID> programmeIDs);

    default Optional<ProgrammeEdition> findByID(ProgrammeEditionID id) {
        return ofIdentity(id);
    }

    List<ProgrammeEditionID> findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(ProgrammeID programmeID, LocalDate date);

    List<ProgrammeEditionID> findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(SchoolYearID schoolYearID, List<ProgrammeID> programmeIDs);
}
