package PAI.controller;

import PAI.domain.*;

import java.util.Optional;

public class CreateProgrammeEditionForCurrentSchoolYearController {

    private ProgrammeEditionRepository _programmeEditionRepository;
    private SchoolYearRepository _schoolYearRepository;

    public CreateProgrammeEditionForCurrentSchoolYearController (ProgrammeEditionRepository programmeEditionRepository, SchoolYearRepository schoolYearRepository) throws Exception {

        if (programmeEditionRepository == null) throw  new Exception("ProgrammeEditionRepository cannot be null");
        if(schoolYearRepository == null) throw new Exception("SchoolYearRepository cannot be null");

        _programmeEditionRepository = programmeEditionRepository;
        _schoolYearRepository = schoolYearRepository;
    }

    public boolean createAProgrammeEditionInTheCurrentSchoolYear (Programme programme){

        SchoolYear currentSchoolYear =_schoolYearRepository.getLatestSchoolYear();
        if(currentSchoolYear == null) return false;

        boolean isCreated = _programmeEditionRepository.createProgrammeEdition(programme, currentSchoolYear);

        if(isCreated) {
            return true;
        }
        return false;
    }

}
