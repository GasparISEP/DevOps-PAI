package PAI.controller;

import PAI.domain.ProgrammeEdition;
import PAI.repository.ProgrammeEditionEnrollmentRepository;
import PAI.repository.ProgrammeEditionRepository;

import java.util.List;

public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    private ProgrammeEditionRepository _programmeEditionRepository;
    private ProgrammeEditionEnrollmentRepository _programmeEditionEnrollmentRepository;

    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(ProgrammeEditionRepository programmeEditionRepository, ProgrammeEditionEnrollmentRepository programmeEditionEnrollmentRepository){

        if(programmeEditionRepository == null){
            throw new IllegalArgumentException("Programme Edition Repository is null");
        }
        _programmeEditionRepository = programmeEditionRepository;

        if(programmeEditionEnrollmentRepository == null){
            throw new IllegalArgumentException("Programme Edition Enrollment Repository is null");
        }
        _programmeEditionEnrollmentRepository = programmeEditionEnrollmentRepository;
    }

    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _programmeEditionRepository.getAllProgrammeEditions();
    }

    public int iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition){

        if(programmeEdition == null){
            throw new IllegalArgumentException("Programme Edition cannot be null");
        }
        return _programmeEditionEnrollmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEdition);
    }
}