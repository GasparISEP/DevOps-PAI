package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.programme.ProgrammeDDD;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
import PAI.repository.studyPlanRepo.IStudyPlanDDDRepository;
import PAI.repository.studyPlanRepo.StudyPlanDDDRepositoryImpl;

import java.util.Optional;

public class US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan {


    IProgrammeDDDRepository _programmeDDDList;
    IStudyPlanDDDRepository _studyPlanDDDRepo;

    public US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan (IProgrammeDDDRepository programmeDDDList, IStudyPlanDDDRepository studyPlanDDDRepo) throws Exception {

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

        ProgrammeDDD programmeDDD;

        try {
            Optional<ProgrammeDDD> optionalProgrammeDDD = _programmeDDDList.ofIdentity(programmeID);
            programmeDDD = optionalProgrammeDDD.orElseThrow(() -> new IllegalArgumentException("Programme with ID " + programmeID + " not found"));
        } catch (IllegalArgumentException e) {
            return false;
        }

        int quantSemester = programmeDDD.getQuantSemesters().getQuantityOfSemesters();
        DurationInYears durationInYears = new DurationInYears(quantSemester);

        int quantityOfEcts = programmeDDD.getQuantEcts().getQuantEcts();
        QuantEcts quantityOfEctsFromProgramme = new QuantEcts(quantityOfEcts);

        _studyPlanDDDRepo.createStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEctsFromProgramme);
        return true;
    }
}