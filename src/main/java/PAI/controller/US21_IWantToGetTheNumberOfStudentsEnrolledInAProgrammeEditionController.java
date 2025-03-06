package PAI.controller;

import PAI.domain.ProgrammeEdition;
import PAI.repository.ProgrammeEditionEnrollmentRepo;

public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    private ProgrammeEditionEnrollmentRepo _programmeEditionEnrollmentRepo;

    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo){

        if(programmeEditionEnrollmentRepo == null){
            throw new IllegalArgumentException("Programme Edition Enrollment Repository is null");
        }

        _programmeEditionEnrollmentRepo = programmeEditionEnrollmentRepo;
    }

    public int iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition){
        return _programmeEditionEnrollmentRepo.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEdition);
    }
}