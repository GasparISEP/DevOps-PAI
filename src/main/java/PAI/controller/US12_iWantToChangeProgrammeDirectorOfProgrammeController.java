package PAI.controller;



import PAI.VOs.ProgrammeID;
import PAI.VOs.TeacherID;
import PAI.domain.Teacher;
import PAI.domain.programme.ProgrammeDDD;
import PAI.repository.ITeacherRepository;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;

import java.util.Optional;


public class US12_iWantToChangeProgrammeDirectorOfProgrammeController {
    IProgrammeDDDRepository _programmeRepo;
    ITeacherRepository _teacherRepo;

    public US12_iWantToChangeProgrammeDirectorOfProgrammeController(IProgrammeDDDRepository programmeList, ITeacherRepository teacherRepo) throws Exception{
        if (programmeList == null ) throw new Exception("List cannot be null");
        _programmeRepo = programmeList;
        _teacherRepo = teacherRepo;
    }

    public Optional <ProgrammeID> findProgrammeIDbyProgramme(ProgrammeDDD programmeDDD) throws Exception{
        return _programmeRepo.findProgrammeIdByProgramme(programmeDDD);
    }

    public Optional <TeacherID> findTeacherIDbyTeacher(Teacher teacher) throws Exception{
        return _teacherRepo.findTeacherIdByTeacher(teacher);
    }

    public boolean changeProgrammeDirector (ProgrammeDDD programmeDDD, Teacher teacher) throws Exception{
        Optional <ProgrammeID> programmeID = findProgrammeIDbyProgramme(programmeDDD);
        Optional<TeacherID> teacherID = findTeacherIDbyTeacher(teacher);
        _programmeRepo.changeProgrammeDirector(programmeID.get(), teacherID.get());
        return true;
    }
}
