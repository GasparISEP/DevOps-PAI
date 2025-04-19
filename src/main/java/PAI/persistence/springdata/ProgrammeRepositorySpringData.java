package PAI.persistence.springdata;

import PAI.domain.programme.Programme;
import PAI.mapper.IProgrammeMapper;
import PAI.persistence.datamodel.ProgrammeDataModel;

import java.util.Optional;

public class ProgrammeRepositorySpringData {

    private final IProgrammeMapper _iProgMapper;
    private final IProgrammeRepositorySpringData _iProgRepo;

    public ProgrammeRepositorySpringData (IProgrammeMapper iProgMapper, IProgrammeRepositorySpringData iProgRepo) {
        if(iProgRepo == null) {
            throw new IllegalArgumentException("iProgrammeRepositorySpringData must not be null");
        }
        if(iProgMapper == null) {
            throw new IllegalArgumentException("iProgrammedMapper must not be null");
        }
        _iProgMapper = iProgMapper;
        _iProgRepo = iProgRepo;
    }

    public Programme save(Programme prog) {
        ProgrammeDataModel programmeDataModel = _iProgMapper.toData(prog);
        if (programmeDataModel != null) {
            _iProgRepo.save(programmeDataModel);
            return _iProgMapper.toDomain(programmeDataModel);
        }
        return null;
    }
}
