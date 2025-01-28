package PAI.controller;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrollmentRepo;

public class US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition {

    ProgrammeEditionEnrollmentRepo _programmeEditionEnrollmentRepo;

    public US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo){
        _programmeEditionEnrollmentRepo = programmeEditionEnrollmentRepo;
    }

    public int IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition){
        return _programmeEditionEnrollmentRepo.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEdition);
    }
}