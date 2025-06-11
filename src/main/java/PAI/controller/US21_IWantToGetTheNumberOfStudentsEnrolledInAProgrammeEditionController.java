package PAI.controller;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    private final IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService;
    private final IProgrammeEditionService iProgrammeEditionService;


    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService, IProgrammeEditionService iProgrammeEditionService) {
        if(iProgrammeEditionEnrolmentService == null) {
            throw new IllegalArgumentException("iProgrammeEditionEnrolmentService cannot be null");
        }
        if(iProgrammeEditionService == null) {
            throw new IllegalArgumentException("iProgrammeEditionService cannot be null");
        }
        this.iProgrammeEditionService = iProgrammeEditionService;
        this.iProgrammeEditionEnrolmentService = iProgrammeEditionEnrolmentService;
    }

    public List<ProgrammeEditionResponseServiceDTO> getAllProgrammeEdition() throws Exception {
        return iProgrammeEditionService.findAllProgrammeEditions();
    }


    public int getTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionID programmeEditionID) throws Exception {

        if (programmeEditionID == null) {
            throw new IllegalArgumentException("Programme Edition ID cannot be null");
        }
        return iProgrammeEditionEnrolmentService.totalStudentsInProgrammeEdition(programmeEditionID);
    }
}