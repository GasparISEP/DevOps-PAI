package PAI.controller;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrollmentRepo;

public class US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition {

    private ProgrammeEditionEnrollmentRepo _programmeEditionEnrollmentRepo;

    public US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo){

        if(programmeEditionEnrollmentRepo == null){
            throw new IllegalArgumentException("Programme Edition Enrollment Repository is null");
        }

        _programmeEditionEnrollmentRepo = programmeEditionEnrollmentRepo;
    }

    public int IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition){
        return _programmeEditionEnrollmentRepo.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEdition);
    }
}