package PAI.controller;
import PAI.VOs.ProgrammeEditionID;
import PAI.service.IProgrammeEditionEnrolmentService;

public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    private final IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService;


    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService) {
        if(iProgrammeEditionEnrolmentService == null) {
            throw new IllegalArgumentException("iProgrammeEditionEnrolmentService cannot be null");
        }
        this.iProgrammeEditionEnrolmentService = iProgrammeEditionEnrolmentService;
    }


    public int getTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionID programmeEditionID) throws Exception {

        if (programmeEditionID == null) {
            throw new IllegalArgumentException("Programme Edition ID cannot be null");
        }
        return iProgrammeEditionEnrolmentService.totalStudentsInProgrammeEdition(programmeEditionID);
    }
}