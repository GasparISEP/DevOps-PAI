package PAI.persistence.springdata;

import PAI.mapper.IProgrammeMapper;

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
}
