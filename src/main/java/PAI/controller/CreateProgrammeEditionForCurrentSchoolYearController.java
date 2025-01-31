package PAI.controller;

import PAI.domain.*;

import java.util.Optional;

public class CreateProgrammeEditionForCurrentSchoolYearController {

    private final ProgrammeEditionRepository _programmeEditionRepository;
    private final SchoolYearRepository _schoolYearRepository;

    public CreateProgrammeEditionForCurrentSchoolYearController (ProgrammeEditionRepository programmeEditionRepository, SchoolYearRepository schoolYearRepository) {

        _programmeEditionRepository = programmeEditionRepository;
        _schoolYearRepository = schoolYearRepository;
    }

    public boolean createAProgrammeEditionInTheCurrentSchoolYear (Programme programme){

        if(_programmeEditionRepository == null || _schoolYearRepository == null) return false;

        SchoolYear currentSchoolYear =_schoolYearRepository.getLatestSchoolYear();
        if(currentSchoolYear == null) return false;

        boolean isCreated = _programmeEditionRepository.createProgrammeEdition(programme, currentSchoolYear);

        return isCreated;
    }

}
