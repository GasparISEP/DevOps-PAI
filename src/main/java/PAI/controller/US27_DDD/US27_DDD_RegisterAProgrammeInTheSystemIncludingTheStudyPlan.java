package PAI.controller.US27_DDD;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.programme.ProgrammeDDD;
import PAI.repository.programmeRepo.ProgrammeDDDRepository;
import PAI.repository.studyPlanRepo.StudyPlanDDDRepository;

import java.util.Optional;

public class US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan {

    ProgrammeDDDRepository _programmeDDDList;
    StudyPlanDDDRepository _studyPlanDDDRepo;

    public US27_DDD_RegisterAProgrammeInTheSystemIncludingTheStudyPlan (ProgrammeDDDRepository programmeDDDList, StudyPlanDDDRepository studyPlanDDDRepo) throws Exception {

        if (programmeDDDList == null) {
            throw new Exception("Programme Repository cannot be null.");
        }

        _programmeDDDList = programmeDDDList;

        if (studyPlanDDDRepo == null) {
            throw new Exception("Study Plan Repository cannot be null.");
        }

        _studyPlanDDDRepo = studyPlanDDDRepo;
    }

    public boolean registerAProgrammeDDDInTheSystem(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, Department department, TeacherID programmeDirectorID) throws Exception {

        _programmeDDDList.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, department, programmeDirectorID);
        return true;
    }

    public boolean createStudyPlanDDD(ProgrammeID programmeID, Date implementationDate) throws Exception {

        ProgrammeDDD programmeDDD;

        try {
            Optional<ProgrammeDDD> optionalProgrammeDDD = _programmeDDDList.findProgrammeByID(programmeID);
            programmeDDD = optionalProgrammeDDD.orElseThrow(() -> new IllegalArgumentException("Programme with ID " + programmeID + " not found"));
        } catch (IllegalArgumentException e) {
            return false;
        }

        int quantSemester = programmeDDD.getQuantSemesters().getQuantityOfSemesters();

        DurationInYears durationInYears = new DurationInYears(quantSemester);

        _studyPlanDDDRepo.createStudyPlan_2(programmeID, implementationDate, durationInYears);
        return true;
    }
}