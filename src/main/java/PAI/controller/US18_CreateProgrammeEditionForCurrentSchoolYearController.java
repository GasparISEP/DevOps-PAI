package PAI.controller;

import PAI.domain.*;

public class US18_CreateProgrammeEditionForCurrentSchoolYearController {

    private final ProgrammeEditionRepository _programmeEditionRepository;
    private final SchoolYearRepository _schoolYearRepository;

    public US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository programmeEditionRepository, SchoolYearRepository schoolYearRepository) {

        _programmeEditionRepository = programmeEditionRepository;
        _schoolYearRepository = schoolYearRepository;
    }

    public boolean createAProgrammeEditionInTheCurrentSchoolYear (Programme programme){

        if(_programmeEditionRepository == null || _schoolYearRepository == null) return false;

        SchoolYear currentSchoolYear =_schoolYearRepository.getCurrentSchoolYear();
        if(currentSchoolYear == null) return false;

        boolean isCreated = _programmeEditionRepository.createProgrammeEdition(programme, currentSchoolYear);

        return isCreated;
    }

}
