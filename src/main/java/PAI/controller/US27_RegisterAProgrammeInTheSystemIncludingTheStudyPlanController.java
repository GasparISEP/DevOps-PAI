package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.Teacher;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.programme.Programme;
import PAI.service.DegreeType.IDegreeTypeService;
import PAI.service.StudyPlan.IStudyPlanService;
import PAI.service.programme.IProgrammeService;

import java.time.LocalDate;
import java.util.Optional;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController {

    IDegreeTypeService _degreeTypeService;
    IProgrammeService _programmeService;
    IStudyPlanService _studyPlanService;

    public US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(IProgrammeService programmeService, IStudyPlanService studyPlanService, IDegreeTypeService degreeTypeService) throws Exception {

        if (programmeService == null) {
            throw new Exception("Programme Service cannot be null.");
        }

        _programmeService = programmeService;

        if (studyPlanService == null) {
            throw new Exception("Study Plan Service cannot be null.");
        }

        _studyPlanService = studyPlanService;


        if (degreeTypeService == null) {
            throw new Exception("Degree Type Repository cannot be null.");
        }

        _degreeTypeService = degreeTypeService;
    }

    public boolean registerProgramme(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector) throws Exception {

        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(name);
        Acronym programmeAcronym = new Acronym(acronym);
        QuantEcts programmeQuantityOfEcts = new QuantEcts(quantityOfEcts);
        QuantSemesters programmeQuantityOfSemesters = new QuantSemesters(quantityOfSemesters);
        DegreeTypeID degreeTypeID = degreeType.identity();
        DepartmentID departmentID = department.identity();
        TeacherID programmeDirectorID = programmeDirector.identity();

        return _programmeService.registerProgramme(programmeName, programmeAcronym, programmeQuantityOfEcts, programmeQuantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
    }

    public boolean createStudyPlan(ProgrammeID programmeID, LocalDate date) throws Exception {

        Programme programme;

        try {
            Optional<Programme> optionalProgramme = _programmeService.getProgrammeByID(programmeID);
            programme = optionalProgramme.orElseThrow(() -> new IllegalArgumentException("Programme with ID " + programmeID + " not found"));
        } catch (IllegalArgumentException e) {
            return false;
        }

        DegreeTypeID degreeTypeID = programme.getDegreeTypeID();
        DegreeType degreeType;

        try {
            Optional<DegreeType> optionalDegreeType = _degreeTypeService.getDegreeTypeById(degreeTypeID);
            degreeType = optionalDegreeType.orElseThrow(() -> new IllegalArgumentException("Degree Type with ID " + degreeTypeID + " not found"));
        } catch (IllegalArgumentException e) {
            return false;
        }

        Date implementationDate = new Date(date);

        int quantSemester = programme.getQuantSemesters().getQuantityOfSemesters();
        DurationInYears durationInYears = new DurationInYears(quantSemester);

        int quantityOfEcts = degreeType.getMaxEcts();
        MaxEcts quantityOfEctsDegreeType = new MaxEcts(quantityOfEcts);

        _studyPlanService.createStudyPlan(programmeID, implementationDate, durationInYears, quantityOfEctsDegreeType);
        return true;
    }
}