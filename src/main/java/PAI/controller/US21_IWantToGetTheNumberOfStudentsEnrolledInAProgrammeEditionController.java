package PAI.controller;

import PAI.VOs.ProgrammeEditionID;
import PAI.domain.ProgrammeEdition;
import PAI.repository.ProgrammeEditionEnrolmentRepositoryImpl;
import PAI.repository.ProgrammeEditionRepository;

import java.util.List;

public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    private ProgrammeEditionRepository _programmeEditionRepository;
    private ProgrammeEditionEnrolmentRepositoryImpl _programmeEditionEnrolmentRepositoryImpl;

    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(ProgrammeEditionRepository programmeEditionRepository, ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImpl){

        if(programmeEditionRepository == null){
            throw new IllegalArgumentException("Programme Edition Repository is null");
        }
        _programmeEditionRepository = programmeEditionRepository;

        if(programmeEditionEnrolmentRepositoryImpl == null){
            throw new IllegalArgumentException("Programme Edition Enrollment Repository is null");
        }
        _programmeEditionEnrolmentRepositoryImpl = programmeEditionEnrolmentRepositoryImpl;
    }

    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _programmeEditionRepository.getAllProgrammeEditions();
    }

    public int iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionID programmeEditionId){

        if(programmeEditionId == null){
            throw new IllegalArgumentException("Programme Edition cannot be null");
        }
        return _programmeEditionEnrolmentRepositoryImpl.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionId);
    }
}