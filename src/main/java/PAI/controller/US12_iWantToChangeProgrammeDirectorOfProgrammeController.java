package PAI.controller;

import PAI.VOs.ProgrammeID;
import PAI.VOs.TeacherID;
import PAI.domain.Programme;
import PAI.domain.programme.ProgrammeDDD;
import PAI.repository.ProgrammeRepository;
import PAI.domain.Teacher;
import PAI.repository.programmeRepo.ProgrammeDDDRepository;

public class US12_iWantToChangeProgrammeDirectorOfProgrammeController {
    ProgrammeDDDRepository _programmeRepo;

    public US12_iWantToChangeProgrammeDirectorOfProgrammeController(ProgrammeDDDRepository programmeList) throws Exception{
        if (programmeList == null ) throw new Exception("List cannot be null");
        _programmeRepo = programmeList;
    }

    public boolean changeProgrammeDirector (ProgrammeDDD programmeDDD, TeacherID teacherID) throws Exception{
        _programmeRepo.changeProgrammeDirector(programmeDDD, teacherID);
        return true;
    }
}
