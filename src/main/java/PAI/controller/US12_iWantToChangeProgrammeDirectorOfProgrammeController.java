package PAI.controller;



import PAI.VOs.ProgrammeID;
import PAI.VOs.TeacherID;
import PAI.domain.Teacher;
import PAI.domain.programme.Programme;
import PAI.service.programme.IProgrammeService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Component
public class US12_iWantToChangeProgrammeDirectorOfProgrammeController {

    private final IProgrammeService programmeService;

    public US12_iWantToChangeProgrammeDirectorOfProgrammeController(IProgrammeService programmeService) {
        if (programmeService == null) {
            throw new IllegalArgumentException("ProgrammeService cannot be null");
        }
        this.programmeService = programmeService;
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

        return programmeService.changeProgrammeDirector(programmeID, programmeDirectorID);
    }
}
