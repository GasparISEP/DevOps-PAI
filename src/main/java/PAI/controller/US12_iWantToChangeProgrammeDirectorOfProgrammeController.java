package PAI.controller;

import PAI.domain.Programme;
import PAI.domain.ProgrammeList;
import PAI.domain.Teacher;

public class US12_iWantToChangeProgrammeDirectorOfProgrammeController {
    ProgrammeList _programmeList;

    public US12_iWantToChangeProgrammeDirectorOfProgrammeController(ProgrammeList programmeList) throws Exception{
        if (programmeList == null ) throw new Exception("List cannot ber null");
        _programmeList = programmeList;
    }

    public boolean changeProgrammeDirector (Programme programme, Teacher teacher) throws Exception{
        _programmeList.changeProgrammeDirector(programme,teacher);
        return true;
    }
}
