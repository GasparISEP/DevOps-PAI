package PAI.controller;

import PAI.VOs.*;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.programme.Programme;
import PAI.repository.degreeTypeRepository.IDegreeTypeRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.repository.studyPlanRepository.IStudyPlanRepository;

import java.util.Optional;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController {

    IDegreeTypeRepository _degreeTypeRepo;
    IProgrammeRepository _programmeDDDList;
    IStudyPlanRepository _studyPlanDDDRepo;

    public US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(IProgrammeRepository programmeDDDList, IStudyPlanRepository studyPlanDDDRepo, IDegreeTypeRepository degreeTypeRepository) throws Exception {

        if (programmeDDDList == null) {
            throw new Exception("Programme Repository cannot be null.");
        }

        _programmeDDDList = programmeDDDList;

        if (studyPlanDDDRepo == null) {
            throw new Exception("Study Plan Repository cannot be null.");
        }

        _studyPlanDDDRepo = studyPlanDDDRepo;


        if (degreeTypeRepository == null) {
            throw new Exception("Degree Type Repository cannot be null.");
        }

        _degreeTypeRepo = degreeTypeRepository;
    }

//    public boolean registerAProgrammeDDDInTheSystem(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception {
//
//        _programmeDDDList.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
//        return true;
//    }

    public boolean createStudyPlanDDD(ProgrammeID programmeID, Date implementationDate) throws Exception {

        Programme programme;

        try {
            Optional<Programme> optionalProgrammeDDD = _programmeDDDList.ofIdentity(programmeID);
            programme = optionalProgrammeDDD.orElseThrow(() -> new IllegalArgumentException("Programme with ID " + programmeID + " not found"));
        } catch (IllegalArgumentException e) {
            return false;
        }

        DegreeTypeID degreeTypeID = programme.getDegreeTypeID();
        DegreeType degreeType;

        try {
            Optional<DegreeType> optionalDegreeType = _degreeTypeRepo.ofIdentity(degreeTypeID);
            degreeType = optionalDegreeType.orElseThrow(() -> new IllegalArgumentException("Degree Type with ID " + degreeTypeID + " not found"));
        } catch (IllegalArgumentException e) {
            return false;
        }


        int quantSemester = programme.getQuantSemesters().getQuantityOfSemesters();
        DurationInYears durationInYears = new DurationInYears(quantSemester);

        int quantityOfEcts = degreeType.getMaxEcts();
        MaxEcts quantityOfEctsDegreeType = new MaxEcts(quantityOfEcts);

        _studyPlanDDDRepo.createStudyPlan(programmeID, implementationDate, durationInYears, quantityOfEctsDegreeType);
        return true;
    }
}