package PAI.controller;

import PAI.domain.*;
import PAI.repository.ProgrammeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class US18_CreateProgrammeEditionForCurrentSchoolYearController {

    private final ProgrammeEditionRepository _programmeEditionRepository;
    private final SchoolYearRepository _schoolYearRepository;
    private final ProgrammeList _programmeList;

    public US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository programmeEditionRepository, SchoolYearRepository schoolYearRepository, ProgrammeList programmeList) {

        _programmeEditionRepository = programmeEditionRepository;
        _schoolYearRepository = schoolYearRepository;
        _programmeList = programmeList;
    }

    public List<String> getAllProgrammeNames(){

        if (_programmeList == null)
            return new ArrayList<>();
        return _programmeList.getAllProgrammeNames();
    }

    public boolean createAProgrammeEditionForTheCurrentSchoolYear(String programmeName){

        if(_programmeEditionRepository == null || _schoolYearRepository == null) return false;

        Optional<Programme> programmeOpt = _programmeList.getProgrammeByName(programmeName);
        Programme programme = programmeOpt.orElse(null);

        SchoolYear currentSchoolYear =_schoolYearRepository.getCurrentSchoolYear();
        if(currentSchoolYear == null) return false;

        boolean isCreated = _programmeEditionRepository.createProgrammeEdition(programme, currentSchoolYear);

        return isCreated;
    }
}
