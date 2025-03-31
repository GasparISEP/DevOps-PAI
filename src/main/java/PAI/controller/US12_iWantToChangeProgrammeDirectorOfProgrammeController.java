package PAI.controller;

import PAI.domain.Programme;
import PAI.repository.ProgrammeRepository;
import PAI.domain.Teacher;

public class US12_iWantToChangeProgrammeDirectorOfProgrammeController {
    ProgrammeRepository _programmeList;

    public US12_iWantToChangeProgrammeDirectorOfProgrammeController(ProgrammeRepository programmeList) throws Exception{
        if (programmeList == null ) throw new Exception("List cannot be null");
        _programmeList = programmeList;
    }

    public boolean changeProgrammeDirector (Programme programme, Teacher teacher) throws Exception{
        _programmeList.changeProgrammeDirector(programme,teacher);
        return true;
    }
}
