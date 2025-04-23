package PAI.persistence.springdata.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.IProgrammeIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;

import java.util.List;
import java.util.Optional;

public class ProgrammeEditionRepositorySpringData implements IProgrammeEditionRepository {

    private final IProgrammeEditionRepositorySpringData _iProgrammeEditionRepositorySpringData;
    private final IProgrammeEditionMapper _iProgrammeEditionMapper;
    private final IProgrammeEditionIdMapper _iProgrammeEditionIdMapper;
    private final IProgrammeIDMapper _iProgrammeIDMapper;
    private final ISchoolYearIDMapper _iSchoolYearIDMapper;

    public ProgrammeEditionRepositorySpringData(
            IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData, IProgrammeEditionMapper iProgrammeEditionMapper,
            IProgrammeEditionIdMapper iProgrammeEditionIdMapper, IProgrammeIDMapper iProgrammeIDMapper, ISchoolYearIDMapper iSchoolYearIDMapper) {

        this._iProgrammeEditionRepositorySpringData = iProgrammeEditionRepositorySpringData;
        this._iProgrammeEditionMapper = iProgrammeEditionMapper;
        this._iProgrammeEditionIdMapper = iProgrammeEditionIdMapper;
        this._iProgrammeIDMapper = iProgrammeIDMapper;
        this._iSchoolYearIDMapper = iSchoolYearIDMapper;
    }

    @Override
    public boolean createProgrammeEdition(ProgrammeID programmeid, SchoolYearID schoolYearid) {
        return false;
    }

    @Override
    public Optional<ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) {
        return Optional.empty();
    }

    @Override
    public ProgrammeEdition save(ProgrammeEdition entity) {
        return null;
    }

    @Override
    public Iterable<ProgrammeEdition> findAll() {
        return null;
    }

    @Override
    public Optional<ProgrammeEdition> ofIdentity(ProgrammeEditionID id) {
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(ProgrammeEditionID id) {
        return false;
    }

    @Override
    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeid) {
        return List.of();
    }

    @Override
    public SchoolYearID getSchoolYearIDByProgrammeEdition(ProgrammeEdition programmeEdition) {
        return null;
    }
}
