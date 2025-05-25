package PAI.controllerRest;

import PAI.service.programmeEdition.IProgrammeEditionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programmeEdition")
public class ProgrammeEditionRestController {

    private final IProgrammeEditionService programmeEditionService;

    public ProgrammeEditionRestController(IProgrammeEditionService programmeEditionService) {
       if (programmeEditionService == null) {
           throw new IllegalArgumentException("ProgrammeEdition service cannot be null");
       }
        this.programmeEditionService = programmeEditionService;
    }

}
