package PAI.controllerRest;


import PAI.dto.programmeEnrolment.IProgrammeEnrolmentMapper;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programmeEnrolment")
public class ProgrammeEnrolmentRestController {

    private final IProgrammeEnrolmentService programmeEnrolmentService;
    private final IProgrammeEnrolmentMapper programmeEnrolmentMapper;



    public ProgrammeEnrolmentRestController(IProgrammeEnrolmentService programmeEnrolmentService, IProgrammeEnrolmentMapper programmeEnrolmentMapper) {
        this.programmeEnrolmentService = programmeEnrolmentService;
        this.programmeEnrolmentMapper = programmeEnrolmentMapper;
    }
}
