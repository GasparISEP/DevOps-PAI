package PAI.persistence.springdata.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionMapper;
import PAI.mapper.schoolYear.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) throws Exception {
        if(programmeid == null) {
            return Optional.empty();
        }
        if(schoolYearid == null) {
            return Optional.empty();
        }
        ProgrammeIDDataModel programmeIDDataModel = iProgrammeIDMapper.toData(programmeid);
        SchoolYearIDDataModel schoolYearIDDataModel = iSchoolYearIDMapper.toDataModel(schoolYearid);
        Optional<ProgrammeEditionDataModel> programmeEditionDataModelOptional =
                iProgrammeEditionRepositorySpringData.findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDataModel(programmeIDDataModel, schoolYearIDDataModel);
        if(programmeEditionDataModelOptional.isPresent()) {
            ProgrammeEditionDataModel programmeEditionDataModel = programmeEditionDataModelOptional.get();
            ProgrammeEditionIdDataModel programmeEditionIdDataModel = programmeEditionDataModel.getProgrammeEditionIDDataModel();
            return Optional.of(iProgrammeEditionIdMapper.toDomain(programmeEditionIdDataModel));
        }
        return Optional.empty();
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

    @Override
    public List<ProgrammeEdition> findByProgrammeIDs(List<ProgrammeID> programmeIDs) {
        try {
            List<ProgrammeEdition> result = new ArrayList<>();
            for (ProgrammeID programmeID : programmeIDs) {
                ProgrammeIDDataModel dataModel = iProgrammeIDMapper.toData(programmeID);
                List<ProgrammeEditionDataModel> dataModels =
                        iProgrammeEditionRepositorySpringData.findProgrammeEditionByProgrammeIDDataModel(dataModel);

                for (ProgrammeEditionDataModel dataModelItem : dataModels) {
                    iProgrammeEditionMapper.toDomain(dataModelItem).ifPresent(result::add);
                }
            }
            return result;
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public List<ProgrammeEditionID> findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(ProgrammeID programmeID, LocalDate date) {
        if (programmeID == null || date == null) {
            return List.of();
        }

        ProgrammeIDDataModel programmeIDDataModel = iProgrammeIDMapper.toData(programmeID);

        List<ProgrammeEditionIdDataModel> dataModels =
                iProgrammeEditionRepositorySpringData.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(programmeIDDataModel, date);

        return dataModels.stream()
                .map(dataModel -> {
                    try {
                        return iProgrammeEditionIdMapper.toDomain(dataModel);
                    } catch (Exception e) {
                        throw new RuntimeException("Erro ao mapear ProgrammeEditionIdDataModel", e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProgrammeEditionID> findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(SchoolYearID schoolYearID, List<ProgrammeID> programmeIDs) {

        if (schoolYearID == null || programmeIDs == null || programmeIDs.isEmpty())
            return List.of();

        SchoolYearIDDataModel schoolYearIDDataModel = iSchoolYearIDMapper.toDataModel(schoolYearID);

        List<ProgrammeIDDataModel> programmeIDDataModels = mapProgrammeIDs(programmeIDs);

        List<ProgrammeEditionIdDataModel> programmeEditionsIdDataModel = iProgrammeEditionRepositorySpringData.findProgrammeEditionIDsBySchoolYearIdAndProgrammeIds(schoolYearIDDataModel, programmeIDDataModels);

        return mapToDomainIDs(programmeEditionsIdDataModel);
    }

    private List<ProgrammeIDDataModel> mapProgrammeIDs(List<ProgrammeID> programmeIDs) {
        List<ProgrammeIDDataModel> programmeIDDataModels = new ArrayList<>();
        for (ProgrammeID programmeID : programmeIDs) {
            programmeIDDataModels.add(iProgrammeIDMapper.toData(programmeID));
        }
        return programmeIDDataModels;
    }

    private List<ProgrammeEditionID> mapToDomainIDs(List<ProgrammeEditionIdDataModel> dataModels) {
        List<ProgrammeEditionID> domainIDs = new ArrayList<>();
        for (ProgrammeEditionIdDataModel dataModel : dataModels) {
            try {
                domainIDs.add(iProgrammeEditionIdMapper.toDomain(dataModel));
            } catch (Exception e) {
                throw new RuntimeException("Error mapping ProgrammeEditionIdDataModel to ProgrammeEditionID: " + dataModel, e);
            }
        }
        return domainIDs;
    }


}
