package PAI.controllerRest;

import PAI.assembler.programme.IProgrammeAssembler;
import PAI.service.programme.IProgrammeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programme")
public class ProgrammeRestController {

    private IProgrammeService _programmeService;
    private IProgrammeAssembler _programmeAssembler;

    public ProgrammeRestController (IProgrammeService programmeService, IProgrammeAssembler programmeAssembler){
        if (programmeService == null) {
            throw new IllegalArgumentException("ProgrammeService cannot be null.");
        }
        if (programmeAssembler == null) {
            throw new IllegalArgumentException("ProgrammeAssembler cannot be null.");
        }

        this._programmeService = programmeService;
        this._programmeAssembler = programmeAssembler;
    }
}
