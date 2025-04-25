package PAI.controller;



import PAI.VOs.ProgrammeID;
import PAI.VOs.TeacherID;
import PAI.domain.Teacher;
import PAI.domain.programme.Programme;
import PAI.repository.ITeacherRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;



public class US12_iWantToChangeProgrammeDirectorOfProgrammeController {
    IProgrammeRepository _programmeRepo;
    ITeacherRepository _teacherRepo;

    public US12_iWantToChangeProgrammeDirectorOfProgrammeController(IProgrammeRepository programmeList, ITeacherRepository teacherRepo) throws Exception{
        if (programmeList == null ) throw new Exception("List cannot be null");
        _programmeRepo = programmeList;
        _teacherRepo = teacherRepo;
    }

    public boolean changeProgrammeDirector(Programme programme, Teacher programmeDirector) throws Exception {
        if (programme == null) {
            throw new IllegalArgumentException("Programme cannot be null");
        }
        if (programmeDirector == null) {
            throw new IllegalArgumentException("Programme Director cannot be null");
        }

        ProgrammeID programmeID = programme.identity();
        TeacherID programmeDirectorID = programmeDirector.identity();

        return _programmeRepo.changeProgrammeDirector(programmeID,programmeDirectorID);
    }
}
