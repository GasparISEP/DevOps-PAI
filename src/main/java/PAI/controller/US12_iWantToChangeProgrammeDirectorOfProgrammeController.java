package PAI.controller;

import PAI.VOs.ProgrammeID;
import PAI.VOs.TeacherID;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;

public class US12_iWantToChangeProgrammeDirectorOfProgrammeController {
    ProgrammeDDDRepositoryImpl _programmeRepo;

    public US12_iWantToChangeProgrammeDirectorOfProgrammeController(ProgrammeDDDRepositoryImpl programmeList) throws Exception{
        if (programmeList == null ) throw new Exception("List cannot be null");
        _programmeRepo = programmeList;
    }

    public boolean changeProgrammeDirector (ProgrammeID programmeID, TeacherID teacherID) throws Exception{
        _programmeRepo.changeProgrammeDirector(programmeID, teacherID);
        return true;
    }
}
