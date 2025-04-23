package PAI.persistence.springdata.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.IProgrammeIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;

import java.util.List;
import java.util.Optional;

public class ProgrammeEditionRepositorySpringDataImpl implements IProgrammeEditionRepository {

    private final IProgrammeEditionRepositorySpringData _iProgrammeEditionRepositorySpringData;
    private final IProgrammeEditionMapper _iProgrammeEditionMapper;
    private final IProgrammeEditionIdMapper _iProgrammeEditionIdMapper;
    private final IProgrammeIDMapper _iProgrammeIDMapper;
    private final ISchoolYearIDMapper _iSchoolYearIDMapper;

    public ProgrammeEditionRepositorySpringDataImpl(
            IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData, IProgrammeEditionMapper iProgrammeEditionMapper,
            IProgrammeEditionIdMapper iProgrammeEditionIdMapper, IProgrammeIDMapper iProgrammeIDMapper, ISchoolYearIDMapper iSchoolYearIDMapper) {

        if(iProgrammeEditionRepositorySpringData == null) {
            throw new IllegalArgumentException("ProgrammeEditionRepositorySpringData cannot be null");
        }
        if(iProgrammeEditionMapper == null) {
            throw new IllegalArgumentException("ProgrammeEditionMapper cannot be null");
        }
        if(iProgrammeEditionIdMapper == null) {
            throw new IllegalArgumentException("ProgrammeEditionIdMapper cannot be null");
        }
        if(iProgrammeIDMapper == null) {
            throw new IllegalArgumentException("ProgrammeIDMapper cannot be null");
        }
        if(iSchoolYearIDMapper == null) {
            throw new IllegalArgumentException("SchoolYearIDMapper cannot be null");
        }
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
    public Optional<ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) throws Exception {
        ProgrammeIDDataModel programmeIDDataModel = _iProgrammeIDMapper.toData(programmeid);
        SchoolYearIDDataModel schoolYearIDDataModel = _iSchoolYearIDMapper.toDataModel(schoolYearid);
        Optional<ProgrammeEditionIdDataModel> programmeEditionIDDataModelOptional =
                _iProgrammeEditionRepositorySpringData.findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDatasModels(programmeIDDataModel, schoolYearIDDataModel);
        if(programmeEditionIDDataModelOptional.isPresent()) {
            ProgrammeEditionIdDataModel programmeEditionIdDataModel = programmeEditionIDDataModelOptional.get();
            return Optional.of(_iProgrammeEditionIdMapper.toDomain(programmeEditionIdDataModel));
        }
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
