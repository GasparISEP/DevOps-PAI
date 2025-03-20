package PAI.controller;

import PAI.domain.ProgrammeEdition;
import PAI.repository.ProgrammeEditionEnrolmentRepository;
import PAI.repository.ProgrammeEditionRepository;

import java.util.List;

public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    private ProgrammeEditionRepository _programmeEditionRepository;
    private ProgrammeEditionEnrolmentRepository _programmeEditionEnrolmentRepository;

    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(ProgrammeEditionRepository programmeEditionRepository, ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository){

        if(programmeEditionRepository == null){
            throw new IllegalArgumentException("Programme Edition Repository is null");
        }
        _programmeEditionRepository = programmeEditionRepository;

        if(programmeEditionEnrolmentRepository == null){
            throw new IllegalArgumentException("Programme Edition Enrollment Repository is null");
        }
        _programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;
    }

    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _programmeEditionRepository.getAllProgrammeEditions();
    }

    public int iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition){

        if(programmeEdition == null){
            throw new IllegalArgumentException("Programme Edition cannot be null");
        }
        return _programmeEditionEnrolmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEdition);
    }
}