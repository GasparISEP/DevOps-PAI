package PAI.controller;


import PAI.VOs.DepartmentID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.repository.*;
import PAI.repository.programmeRepository.IProgrammeRepository;

import java.util.List;
import java.util.Set;

public class US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController {
    private final IProgrammeEditionEnrolmentRepository _PEERepo;
    private final ISchoolYearRepository _schoolYearRepository;
    private final IDepartmentRepository _departmentRepository;
    private final IProgrammeRepository _programmeRepository;


    public US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(
            IProgrammeEditionEnrolmentRepository PEERepo,
            ISchoolYearRepository schoolYearRepository,
            IDepartmentRepository departmentRepository,
            IProgrammeRepository programmeRepository
    ) {
        if (PEERepo == null || schoolYearRepository == null || departmentRepository == null || programmeRepository == null){
            throw new IllegalArgumentException("Repositories cannot be null.");
        }
        _PEERepo=PEERepo;
        _schoolYearRepository=schoolYearRepository;
        _departmentRepository=departmentRepository;
        _programmeRepository =programmeRepository;
    }

    public int countStudentsInProgrammesFromDepartmentInSchoolYear(DepartmentID department, SchoolYearID schoolYear) {
        if(department==null || schoolYear==null){
            throw new  IllegalArgumentException("Department or SchoolYear cannot be null");
        }
        if(!_schoolYearRepository.schoolYearExistsByID(schoolYear)){
            throw new  IllegalArgumentException("SchoolYear does not exist.");
        }
        if(!_departmentRepository.departmentExists(department)){
            throw new  IllegalArgumentException("Department does not exist.");
        }
        List<ProgrammeID> programmeIDs = _programmeRepository.findProgrammeByDepartment(department);

        return _PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(schoolYear,programmeIDs);
    }

    public Set<DepartmentID> getAllDepartmentID() {
        return _departmentRepository.getDepartmentIDs();
    }

    public List<SchoolYearID> getAllSchoolYearsIDs() {
        return _schoolYearRepository.getAllSchoolYearsIDs();
    }
}