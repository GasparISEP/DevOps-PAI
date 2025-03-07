package PAI.controller;

import PAI.domain.ProgrammeEdition;
import PAI.repository.ProgrammeEditionEnrollmentRepo;
import PAI.repository.ProgrammeEditionRepository;

import java.util.List;

public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    private ProgrammeEditionRepository _programmeEditionRepository;
    private ProgrammeEditionEnrollmentRepo _programmeEditionEnrollmentRepo;

    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(ProgrammeEditionRepository programmeEditionRepository, ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo){

        if(programmeEditionRepository == null){
            throw new IllegalArgumentException("Programme Edition Repository is null");
        }
        _programmeEditionRepository = programmeEditionRepository;

        if(programmeEditionEnrollmentRepo == null){
            throw new IllegalArgumentException("Programme Edition Enrollment Repository is null");
        }
        _programmeEditionEnrollmentRepo = programmeEditionEnrollmentRepo;
    }

    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _programmeEditionRepository.getAllProgrammeEditions();
    }

    public int iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition){

        if(programmeEdition == null){
            throw new IllegalArgumentException("Programme Edition cannot be null");
        }
        return _programmeEditionEnrollmentRepo.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEdition);
    }
}