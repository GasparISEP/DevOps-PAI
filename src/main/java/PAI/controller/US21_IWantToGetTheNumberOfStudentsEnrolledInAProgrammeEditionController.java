package PAI.controller;

import PAI.VOs.ProgrammeEditionID;
import PAI.domain.ProgrammeEdition;
import PAI.repository.IProgrammeEditionEnrolmentRepository;
import PAI.repository.ProgrammeEditionRepository;

import java.util.List;

/*
public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    //private final ProgrammeEditionRepository _programmeEditionRepository;
    private final IProgrammeEditionEnrolmentRepository _programmeEditionEnrolmentRepository;

    //public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(ProgrammeEditionRepository programmeEditionRepository, ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository){
    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController( IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository){
       // if(programmeEditionRepository == null){
            //throw new IllegalArgumentException("Programme Edition Repository is null");
        //}
        //_programmeEditionRepository = programmeEditionRepository;

        if(programmeEditionEnrolmentRepository == null){
            throw new IllegalArgumentException("Programme Edition Enrollment Repository is null");
        }
        _programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;
 }*/

public class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController {

    private final IProgrammeEditionEnrolmentRepository _programmeEditionEnrolmentRepository;


    public US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository) {

        if (programmeEditionEnrolmentRepository == null) {
            throw new IllegalArgumentException("Programme Edition Enrollment Repository is null");
        }
        _programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;
    }

/*    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _programmeEditionRepository.getAllProgrammeEditions();
    }
*/
    public int iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionID programmeEditionID) {

        if (programmeEditionID == null) {
            throw new IllegalArgumentException("Programme Edition ID cannot be null");
        }
        return _programmeEditionEnrolmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID);
    }
}