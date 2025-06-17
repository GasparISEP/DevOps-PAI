package PAI.persistence.springdata.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.mapper.department.IDepartmentIDMapper;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programme.IProgrammeMapper;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class ProgrammeRepositorySpringDataImpl implements IProgrammeRepository {

    private final IProgrammeMapper _iProgMapper;
    private final IProgrammeRepositorySpringData _iProgRepo;
    private final IProgrammeIDMapper _iProgIDMapper;
    private final IDepartmentIDMapper _iDepartmentIDMapper;

    public ProgrammeRepositorySpringDataImpl(IProgrammeMapper iProgMapper, IProgrammeRepositorySpringData iProgRepo, IProgrammeIDMapper iProgIDMapper, IDepartmentIDMapper iDepartmentIDMapper) {
        if(iProgRepo == null) {
            throw new IllegalArgumentException("iProgrammeRepositorySpringData must not be null");
        }
        if(iProgMapper == null) {
            throw new IllegalArgumentException("iProgrammedMapper must not be null");
        }
        if(iProgIDMapper == null) {
            throw new IllegalArgumentException("iProgrammedIDMapper must not be null");
        }
        if(iDepartmentIDMapper == null) {
            throw new IllegalArgumentException("iDepartmentIDMapper must not be null");
        }
        _iProgMapper = iProgMapper;
        _iProgRepo = iProgRepo;
        _iProgIDMapper = iProgIDMapper;
        _iDepartmentIDMapper = iDepartmentIDMapper;
    }

    public Programme save(Programme prog) {
        ProgrammeDataModel programmeDataModel = _iProgMapper.toData(prog);
        if (programmeDataModel != null) {
            _iProgRepo.save(programmeDataModel);
            return _iProgMapper.toDomain(programmeDataModel);
        }
        return null;
    }

    public Programme update(ProgrammeIDDataModel id,Programme prog) {
        if(!_iProgRepo.existsById(id))
            throw new EntityNotFoundException("Programme not found!");

        ProgrammeDataModel programmeDataModel = _iProgMapper.toData(prog);
        if (programmeDataModel != null) {
           ProgrammeDataModel updated = _iProgRepo.save(programmeDataModel);
           return _iProgMapper.toDomain(updated);
        }
        return null;
    }

    public List<Programme> findAll(){
        List <ProgrammeDataModel> programmeDataModelList = _iProgRepo.findAll();
        List<Programme> domainList = new ArrayList<>();
        for (ProgrammeDataModel programmeDataModel : programmeDataModelList) {
            Programme programme = _iProgMapper.toDomain(programmeDataModel);
            domainList.add(programme);
        }
        return domainList;
    }

    public Optional<Programme> ofIdentity(ProgrammeID id) {
        ProgrammeIDDataModel idDM = _iProgIDMapper.toData(id);
        Optional<ProgrammeDataModel> dataModelOptional = _iProgRepo.findById(idDM);

        if (dataModelOptional.isPresent()) {
            Programme programme = _iProgMapper.toDomain(dataModelOptional.get());
            return Optional.of(programme);
        }

        return Optional.empty();
    }

    public boolean containsOfIdentity(ProgrammeID id) {
        ProgrammeIDDataModel idDM = _iProgIDMapper.toData(id);
        return _iProgRepo.existsById(idDM);
    }

    public List<ProgrammeID> findProgrammesIdByDepartmentId(DepartmentID departmentID){

        DepartmentIDDataModel dataModel = _iDepartmentIDMapper.toDataModel(departmentID);
        List<ProgrammeIDDataModel> programmes = _iProgRepo.findProgrammesIdByDepartmentId(dataModel);
        List<ProgrammeID> programmeIDs = new ArrayList<>();
        for (ProgrammeIDDataModel programmeIDDataModel : programmes) {
            ProgrammeID programmeID = _iProgIDMapper.toDomain(programmeIDDataModel);
            programmeIDs.add(programmeID);
        }
        return programmeIDs;
    }

    @Override
    public boolean existsByName(NameWithNumbersAndSpecialChars name) {
        return _iProgRepo.existsByName(name.getNameWithNumbersAndSpecialChars());
    }

    @Override
    public boolean existsByAcronym(Acronym acronym) {
        return _iProgRepo.existsByAcronym(acronym.getAcronym());
    }
}
