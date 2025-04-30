package PAI.persistence.springdata.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
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

    private final IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData;
    private final IProgrammeEditionMapper iProgrammeEditionMapper;
    private final IProgrammeEditionIdMapper iProgrammeEditionIdMapper;
    private final IProgrammeIDMapper iProgrammeIDMapper;
    private final ISchoolYearIDMapper iSchoolYearIDMapper;

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
        this.iProgrammeEditionRepositorySpringData = iProgrammeEditionRepositorySpringData;
        this.iProgrammeEditionMapper = iProgrammeEditionMapper;
        this.iProgrammeEditionIdMapper = iProgrammeEditionIdMapper;
        this.iProgrammeIDMapper = iProgrammeIDMapper;
        this.iSchoolYearIDMapper = iSchoolYearIDMapper;
    }

    @Override
    public Optional<ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) throws Exception {
        if(programmeid == null) {
            return Optional.empty();
        }
        if(schoolYearid == null) {
            return Optional.empty();
        }
        ProgrammeIDDataModel programmeIDDataModel = iProgrammeIDMapper.toData(programmeid);
        SchoolYearIDDataModel schoolYearIDDataModel = iSchoolYearIDMapper.toDataModel(schoolYearid);
        Optional<ProgrammeEditionIdDataModel> programmeEditionIDDataModelOptional =
                iProgrammeEditionRepositorySpringData.findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDatasModels(programmeIDDataModel, schoolYearIDDataModel);
        if(programmeEditionIDDataModelOptional.isPresent()) {
            ProgrammeEditionIdDataModel programmeEditionIdDataModel = programmeEditionIDDataModelOptional.get();
            return Optional.of(iProgrammeEditionIdMapper.toDomain(programmeEditionIdDataModel));
        }
        return Optional.empty();
    }

    @Override
    public ProgrammeEdition save(ProgrammeEdition entity) {
        if (entity == null) {
            return null;
        }
        try {
            Optional<ProgrammeEditionDataModel> programmeEditionDataModel = iProgrammeEditionMapper.toDataModel(entity);
            if (programmeEditionDataModel.isPresent()) {
                iProgrammeEditionRepositorySpringData.save(programmeEditionDataModel.get());
                Optional<ProgrammeEdition> programmeEdition = iProgrammeEditionMapper.toDomain(programmeEditionDataModel.get());
                if(programmeEdition.isPresent()) {
                    return programmeEdition.get();
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public Iterable<ProgrammeEdition> findAll() {
        List<ProgrammeEdition> programmeEditions = new ArrayList<>();
        List<ProgrammeEditionDataModel> programmeEditionDataModels = iProgrammeEditionRepositorySpringData.findAll();
        for(ProgrammeEditionDataModel programmeEditionDataModel : programmeEditionDataModels) {
            try {
                Optional<ProgrammeEdition> programmeEdition = iProgrammeEditionMapper.toDomain(programmeEditionDataModel);
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
            ProgrammeEditionIdDataModel programmeEditionIdDataModel = iProgrammeEditionIdMapper.toDataModel(id);
            Optional<ProgrammeEditionDataModel> programmeEditionDataModel = iProgrammeEditionRepositorySpringData.findById(programmeEditionIdDataModel);
            if(programmeEditionDataModel.isPresent()) {
                return iProgrammeEditionMapper.toDomain(programmeEditionDataModel.get());
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
            return iProgrammeEditionRepositorySpringData.existsById(iProgrammeEditionIdMapper.toDataModel(id));
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
            ProgrammeIDDataModel programmeIDDataModel = iProgrammeIDMapper.toData(programmeid);
            List<ProgrammeEditionDataModel> programmeEditionDataModels =
                    iProgrammeEditionRepositorySpringData.findProgrammeEditionByProgrammeIDDataModel(programmeIDDataModel);

            List<ProgrammeEdition> programmeEditions = new ArrayList<>();
            for (ProgrammeEditionDataModel programmeEditionDataModel : programmeEditionDataModels) {
                Optional<ProgrammeEdition> programmeEdition = iProgrammeEditionMapper.toDomain(programmeEditionDataModel);
                programmeEdition.ifPresent(programmeEditions::add);
            }
            return programmeEditions;
        } catch (Exception e) {
            return null;
        }
    }
}
