package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.domain.programme.Programme;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepositoryDDD;
import PAI.repository.programmeRepository.IProgrammeDDDRepository;

import java.util.List;
import java.util.Optional;

public class US18_CreateProgrammeEditionForCurrentSchoolYearController {

    private final IProgrammeEditionRepositoryDDD _programmeEditionRepository;
    private final ISchoolYearRepository _schoolYearRepository;
    private final IProgrammeDDDRepository _programmeRepository;

    public US18_CreateProgrammeEditionForCurrentSchoolYearController(IProgrammeEditionRepositoryDDD programmeEditionRepository, ISchoolYearRepository schoolYearRepository, IProgrammeDDDRepository programmeRepository) throws Exception {

        if (programmeEditionRepository == null)
            throw new Exception("Programme Edition Repository cannot be null");
        if (schoolYearRepository == null)
            throw new Exception("School Year Repository cannot be null");
        if (programmeRepository == null)
            throw new Exception("Programme Repository cannot be null");

        _programmeEditionRepository = programmeEditionRepository;
        _schoolYearRepository = schoolYearRepository;
        _programmeRepository = programmeRepository;
    }

    public List<NameWithNumbersAndSpecialChars> getAllProgrammeNames(){

        return _programmeRepository.getAllProgrammeNames();
    }

    public boolean createAProgrammeEditionForTheCurrentSchoolYear(NameWithNumbersAndSpecialChars programmeName){

        Optional<Programme> programmeOpt = _programmeRepository.getProgrammeByName(programmeName);
        Programme programme = programmeOpt.orElse(null);

        ProgrammeID pID;
        if (programme == null)
            return false;

        pID = programme.identity();

        SchoolYear currentSchoolYear =_schoolYearRepository.getCurrentSchoolYear();
        if(currentSchoolYear == null)
            return false;

        SchoolYearID sYID = currentSchoolYear.identity();

        boolean isCreated = _programmeEditionRepository.createProgrammeEdition(pID, sYID);
        return isCreated;
    }
}
