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
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
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
        if(programmeid == null) {
            return Optional.empty();
        }
        if(schoolYearid == null) {
            return Optional.empty();
        }
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
    public ProgrammeEdition save(ProgrammeEdition entity) throws Exception {
        if (entity == null) {
            return null;
        }
        Optional<ProgrammeEditionDataModel> programmeEditionDataModel = _iProgrammeEditionMapper.toDataModel(entity);
        if (programmeEditionDataModel.isPresent()) {
            _iProgrammeEditionRepositorySpringData.save(programmeEditionDataModel.get());
            Optional<ProgrammeEdition> programmeEdition = _iProgrammeEditionMapper.toDomain(programmeEditionDataModel.get());
            if(programmeEdition.isPresent()) {
                return programmeEdition.get();
            }
        }
        return null;
    }

    @Override
    public Iterable<ProgrammeEdition> findAll() {
        List<ProgrammeEdition> programmeEditions = new ArrayList<>();
        List<ProgrammeEditionDataModel> programmeEditionDataModels = _iProgrammeEditionRepositorySpringData.findAll();
        for(ProgrammeEditionDataModel programmeEditionDataModel : programmeEditionDataModels) {
            try {
                Optional<ProgrammeEdition> programmeEdition = _iProgrammeEditionMapper.toDomain(programmeEditionDataModel);
                programmeEdition.ifPresent(programmeEditions::add);
            } catch (Exception e) {
                return null;
            }
        }
        return programmeEditions;
    }

    @Override
    public Optional<ProgrammeEdition> ofIdentity(ProgrammeEditionID id) {
        if(id == null)  {
            return Optional.empty();
        }
        try {
            ProgrammeEditionIdDataModel programmeEditionIdDataModel = _iProgrammeEditionIdMapper.toDataModel(id);
            Optional<ProgrammeEditionDataModel> programmeEditionDataModel = _iProgrammeEditionRepositorySpringData.findById(programmeEditionIdDataModel);
            if(programmeEditionDataModel.isPresent()) {
                return _iProgrammeEditionMapper.toDomain(programmeEditionDataModel.get());
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(ProgrammeEditionID id) {
        if (id == null) {
            return false;
        }
        try {
            return _iProgrammeEditionRepositorySpringData.existsById(_iProgrammeEditionIdMapper.toDataModel(id));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeid) {
        if (programmeid == null) {
            return null;
        }

        try {
            ProgrammeIDDataModel programmeIDDataModel = _iProgrammeIDMapper.toData(programmeid);
            List<ProgrammeEditionDataModel> programmeEditionDataModels =
                    _iProgrammeEditionRepositorySpringData.findProgrammeEditionByProgrammeIDDataModel(programmeIDDataModel);

            List<ProgrammeEdition> programmeEditions = new ArrayList<>();
            for (ProgrammeEditionDataModel programmeEditionDataModel : programmeEditionDataModels) {
                Optional<ProgrammeEdition> programmeEdition = _iProgrammeEditionMapper.toDomain(programmeEditionDataModel);
                programmeEdition.ifPresent(programmeEditions::add);
            }
            return programmeEditions;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SchoolYearID getSchoolYearIDByProgrammeEdition(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            return null;
        }

        try {
            Optional<ProgrammeEditionDataModel> programmeEditionDataModel = _iProgrammeEditionMapper.toDataModel(programmeEdition);
            if (programmeEditionDataModel.isPresent()) {
                SchoolYearIDDataModel schoolYearIDDataModel = programmeEditionDataModel.get().getSchoolYearIDDataModel();
                return _iSchoolYearIDMapper.toDomain(schoolYearIDDataModel);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
