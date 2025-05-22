package PAI.controllerRest;

import PAI.service.programme.IProgrammeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programme")
public class ProgrammeRestController {

    private IProgrammeService _programmeService;

    public ProgrammeRestController (IProgrammeService programmeService){
        if (programmeService == null) {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }
        this._programmeService = programmeService;
    }
}
