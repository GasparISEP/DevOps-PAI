package PAI.controller;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrollmentRepo;
import PAI.domain.ProgrammeEditionRepository;
import java.util.List;

public class US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition {

    private ProgrammeEditionEnrollmentRepo _programmeEditionEnrollmentRepo;
    private ProgrammeEditionRepository _programmeEditionRepository;

    public US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo, ProgrammeEditionRepository programmeEditionRepository){

        if(programmeEditionEnrollmentRepo == null){
            throw new IllegalArgumentException("Programme Edition Enrollment Repository is null");
        }

        if(programmeEditionRepository == null){
            throw new IllegalArgumentException("Programme Edition Repository is null");
        }

        _programmeEditionEnrollmentRepo = programmeEditionEnrollmentRepo;
        _programmeEditionRepository = programmeEditionRepository;
    }

    public int IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition){
        return _programmeEditionEnrollmentRepo.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEdition);
    }
}