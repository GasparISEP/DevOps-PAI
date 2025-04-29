package PAI.controller;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.service.IProgrammeEditionEnrolmentService;

public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    private final IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService;
    private final IProgrammeEditionRepository iProgrammeEditionRepository;


    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService, IProgrammeEditionRepository iProgrammeEditionRepository) {
        if(iProgrammeEditionEnrolmentService == null) {
            throw new IllegalArgumentException("iProgrammeEditionEnrolmentService cannot be null");
        }
        if(iProgrammeEditionRepository == null) {
            throw new IllegalArgumentException("iProgrammeEditionRepository cannot be null");
        }
        this.iProgrammeEditionRepository = iProgrammeEditionRepository;
        this.iProgrammeEditionEnrolmentService = iProgrammeEditionEnrolmentService;
    }

    public Iterable<ProgrammeEdition> getAllProgrammeEdition() {
        return iProgrammeEditionRepository.findAll();
    }


    public int getTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionID programmeEditionID) throws Exception {

        if (programmeEditionID == null) {
            throw new IllegalArgumentException("Programme Edition ID cannot be null");
        }
        return iProgrammeEditionEnrolmentService.totalStudentsInProgrammeEdition(programmeEditionID);
    }
}