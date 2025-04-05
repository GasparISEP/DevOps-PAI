package PAI.controller;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.repository.programmeRepository.IProgrammeDDDRepository;
import PAI.repository.studyPlanRepository.IStudyPlanDDDRepository;

import java.util.Optional;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController {


    IProgrammeDDDRepository _programmeDDDList;
    IStudyPlanDDDRepository _studyPlanDDDRepo;

    public US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(IProgrammeDDDRepository programmeDDDList, IStudyPlanDDDRepository studyPlanDDDRepo) throws Exception {

        if (programmeDDDList == null) {
            throw new Exception("Programme Repository cannot be null.");
        }

        _programmeDDDList = programmeDDDList;

        if (studyPlanDDDRepo == null) {
            throw new Exception("Study Plan Repository cannot be null.");
        }

        _studyPlanDDDRepo = studyPlanDDDRepo;
    }

    public boolean registerAProgrammeDDDInTheSystem(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception {

        _programmeDDDList.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
        return true;
    }

    public boolean createStudyPlanDDD(ProgrammeID programmeID, Date implementationDate) throws Exception {

        Programme programme;

        try {
            Optional<Programme> optionalProgrammeDDD = _programmeDDDList.ofIdentity(programmeID);
            programme = optionalProgrammeDDD.orElseThrow(() -> new IllegalArgumentException("Programme with ID " + programmeID + " not found"));
        } catch (IllegalArgumentException e) {
            return false;
        }

        int quantSemester = programme.getQuantSemesters().getQuantityOfSemesters();
        DurationInYears durationInYears = new DurationInYears(quantSemester);

        int quantityOfEcts = programme.getQuantEcts().getQuantEcts();
        QuantEcts quantityOfEctsFromProgramme = new QuantEcts(quantityOfEcts);

        _studyPlanDDDRepo.createStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEctsFromProgramme);
        return true;
    }
}