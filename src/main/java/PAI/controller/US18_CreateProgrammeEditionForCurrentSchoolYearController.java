package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepositoryDDD;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;

import java.util.List;

public class US18_CreateProgrammeEditionForCurrentSchoolYearController {

    private final IProgrammeEditionRepositoryDDD _programmeEditionRepository;
    private final ISchoolYearRepository _schoolYearRepository;
    private final IProgrammeDDDRepository _programmeRepository;

    public US18_CreateProgrammeEditionForCurrentSchoolYearController(IProgrammeEditionRepositoryDDD programmeEditionRepository, ISchoolYearRepository schoolYearRepository, IProgrammeDDDRepository programmeRepository) throws Exception {

        _programmeEditionRepository = programmeEditionRepository;
        _schoolYearRepository = schoolYearRepository;
        _programmeRepository = programmeRepository;
    }

    public List<NameWithNumbersAndSpecialChars> getAllProgrammeNames(){

        /*if (_programmeRepository == null)
            return new ArrayList<>();
        return _programmeRepository.getAllProgrammeNames();*/
        return null;
    }

    public boolean createAProgrammeEditionForTheCurrentSchoolYear(NameWithNumbersAndSpecialChars programmeName){

/*        if(_programmeEditionRepository == null || _schoolYearRepository == null) return false;

        Optional<Programme> programmeOpt = _programmeRepository.getProgrammeByName(programmeName);
        Programme programme = programmeOpt.orElse(null);

        SchoolYear currentSchoolYear =_schoolYearRepository.getCurrentSchoolYear();
        if(currentSchoolYear == null) return false;

        boolean isCreated = _programmeEditionRepository.createProgrammeEdition(programme, currentSchoolYear);

        return isCreated;*/
        return false;
    }
}
