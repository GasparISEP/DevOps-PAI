package PAI.controller;

import PAI.VOs.*;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.Programme.ProgrammeVOsDTO;
import PAI.service.studyPlan.IStudyPlanService;
import PAI.service.programme.IProgrammeService;

import java.time.LocalDate;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController {

    private final IProgrammeService _programmeService;
    private final IStudyPlanService _studyPlanService;
    private final IStudyPlanAssembler _studyPlanAssembler;


    public US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(IProgrammeService programmeService, IStudyPlanService studyPlanService, IStudyPlanAssembler studyPlanAssembler) throws Exception {

        if (programmeService == null) {
            throw new Exception("Programme Service cannot be null.");
        }
        if (studyPlanService == null) {
            throw new Exception("Study Plan Service cannot be null.");
        }
        if (studyPlanAssembler == null) {
            throw new Exception("Study Plan Assembler cannot be null.");
        }

        this._programmeService = programmeService;
        this._studyPlanService = studyPlanService;
        this._studyPlanAssembler = studyPlanAssembler;
    }

    public boolean registerProgrammeIncludingStudyPlan(String name, String acronym, int maxEcts,
                                       int quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID,
                                       TeacherID programmeDirectorID, String studyPlanStartDate) {

        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(name);
        Acronym programmeAcronym = new Acronym(acronym);
        MaxEcts programmeMaxOfEcts = new MaxEcts(maxEcts);
        QuantSemesters programmeQuantityOfSemesters = new QuantSemesters(quantityOfSemesters);

        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(programmeName, programmeAcronym, programmeMaxOfEcts, programmeQuantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);

        try {
            _programmeService.registerProgramme(programmeVOsDTO);
        } catch (Exception e) {
            System.err.println("Failed to register programme: " + e.getMessage());
            return false;
        }

        RegisterStudyPlanCommand studyPlanCommand = _studyPlanAssembler.toCommand(name, acronym, LocalDate.parse(studyPlanStartDate));

        try {
            _studyPlanService.createStudyPlan(studyPlanCommand);
            System.out.println("Programme, including study plan registered successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Failed to register study plan: " + e.getMessage());
            return false;
        }
    }
}