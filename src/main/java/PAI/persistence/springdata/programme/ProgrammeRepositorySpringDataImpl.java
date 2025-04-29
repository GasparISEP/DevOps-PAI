package PAI.persistence.springdata.programme;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.domain.programme.Programme;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programme.IProgrammeMapper;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//Should Implement IProgrammeRepository
public class ProgrammeRepositorySpringDataImpl {

    private final IProgrammeMapper _iProgMapper;
    private final IProgrammeRepositorySpringData _iProgRepo;
    private final IProgrammeIDMapper _iProgIdMapper;

    public ProgrammeRepositorySpringDataImpl(IProgrammeMapper iProgMapper, IProgrammeRepositorySpringData iProgRepo, IProgrammeIDMapper iProgIdMapper) {
        if(iProgRepo == null) {
            throw new IllegalArgumentException("iProgrammeRepositorySpringData must not be null");
        }
        if(iProgMapper == null) {
            throw new IllegalArgumentException("iProgrammedMapper must not be null");
        }
        if (iProgIdMapper == null) {
            throw new IllegalArgumentException("iProgrammeIDMapper must not be null");
        }
        _iProgMapper = iProgMapper;
        _iProgRepo = iProgRepo;
        _iProgIdMapper = iProgIdMapper;
    }

    public Programme save(Programme prog) {
        ProgrammeDataModel programmeDataModel = _iProgMapper.toData(prog);
        if (programmeDataModel != null) {
            _iProgRepo.save(programmeDataModel);
            return _iProgMapper.toDomain(programmeDataModel);
        }
        return null;
    }

    public Programme update(ProgrammeID id,Programme prog) {
        if(!_iProgRepo.existsById(id.toString()))
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
        Optional<ProgrammeDataModel> dataModelOptional = _iProgRepo.findById(id.toString());

        if (dataModelOptional.isPresent()) {
            Programme programme = _iProgMapper.toDomain(dataModelOptional.get());
            return Optional.of(programme);
        }

        return Optional.empty();
    }

    public boolean containsOfIdentity(ProgrammeID id) {
        return _iProgRepo.existsById(id.toString());
    }
}
