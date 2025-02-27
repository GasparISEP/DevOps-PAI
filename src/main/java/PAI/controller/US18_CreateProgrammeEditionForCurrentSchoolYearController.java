package PAI.controller;

import PAI.domain.*;

import java.util.List;

public class US18_CreateProgrammeEditionForCurrentSchoolYearController {

    private final ProgrammeEditionRepository _programmeEditionRepository;
    private final SchoolYearRepository _schoolYearRepository;
    private final ProgrammeList _programmeList;

    public US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository programmeEditionRepository, SchoolYearRepository schoolYearRepository, ProgrammeList programmeList) {

        _programmeEditionRepository = programmeEditionRepository;
        _schoolYearRepository = schoolYearRepository;
        _programmeList = programmeList;
    }

    public boolean createAProgrammeEditionInTheCurrentSchoolYear (Programme programme){

        if(_programmeEditionRepository == null || _schoolYearRepository == null) return false;

        SchoolYear currentSchoolYear =_schoolYearRepository.getCurrentSchoolYear();
        if(currentSchoolYear == null) return false;

        boolean isCreated = _programmeEditionRepository.createProgrammeEdition(programme, currentSchoolYear);

        return isCreated;
    }

    public List<Programme> getProgrammeList(){
        return _programmeList.getAllProgrammes();
    }
}
