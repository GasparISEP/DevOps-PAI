package PAI.persistence.springdata;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.programme.Programme;
import PAI.mapper.IProgrammeMapper;
import PAI.persistence.datamodel.ProgrammeDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//Should Implement IProgrammeRepository
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

    //US18
    public List<NameWithNumbersAndSpecialChars> getAllProgrammeNames (){
        Iterable<String> programmesNamesListData = _iProgRepo.findAllProgrammeNames();

        List<NameWithNumbersAndSpecialChars> programmeNamesListVO = new ArrayList<>();

        for(String programmeNameData : programmesNamesListData) {
            NameWithNumbersAndSpecialChars programmeNameVO = new NameWithNumbersAndSpecialChars(programmeNameData);
            programmeNamesListVO.add(programmeNameVO);
        }

        return programmeNamesListVO;
    }
}
