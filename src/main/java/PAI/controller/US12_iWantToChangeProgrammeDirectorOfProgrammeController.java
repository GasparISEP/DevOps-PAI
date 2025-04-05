package PAI.controller;



import PAI.VOs.ProgrammeID;
import PAI.VOs.TeacherID;
import PAI.domain.Teacher;
import PAI.domain.programme.Programme;
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

    public Optional <ProgrammeID> findProgrammeIDbyProgramme(Programme programme) throws Exception{
        return _programmeRepo.findProgrammeIdByProgramme(programme);
    }

    public Optional <TeacherID> findTeacherIDbyTeacher(Teacher teacher) throws Exception{
        return _teacherRepo.findTeacherIdByTeacher(teacher);
    }

    public boolean changeProgrammeDirector (Programme programme, Teacher teacher) throws Exception{
        Optional <ProgrammeID> programmeID = findProgrammeIDbyProgramme(programme);
        Optional<TeacherID> teacherID = findTeacherIDbyTeacher(teacher);
        return  _programmeRepo.changeProgrammeDirector(programmeID.get(), teacherID.get());

    }
}
