package PAI.controller;


import PAI.domain.*;
import PAI.repository.DepartmentRepository;
import PAI.repository.ProgrammeEditionEnrollmentRepo;

public class US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController {
    private ProgrammeEditionEnrollmentRepo _PEERepo;
    private SchoolYearRepository _schoolYearRepository;
    private DepartmentRepository _departmentRepository;

    public US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(
            ProgrammeEditionEnrollmentRepo PEERepo,
            SchoolYearRepository schoolYearRepository,
            DepartmentRepository departmentRepository
    ) {
        if (PEERepo == null || schoolYearRepository == null || departmentRepository == null){
            throw new IllegalArgumentException("Repositories cannot be null.");
        }
        _PEERepo=PEERepo;
        _schoolYearRepository=schoolYearRepository;
        _departmentRepository=departmentRepository;
    }

    public int countStudentsInProgrammesFromDepartmentInSchoolYear(Department department, SchoolYear schoolYear) {
        if(department==null || schoolYear==null){
            throw new  IllegalArgumentException("Department or SchoolYear cannot be null");
        }
        if(!_schoolYearRepository.schoolYearExists(schoolYear)){
            throw new  IllegalArgumentException("SchoolYear does not exist.");
        }
        if(!_departmentRepository.departmentExists(department)){
            throw new  IllegalArgumentException("Department does not exist.");
        }
        return _PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(department,schoolYear);
    }
}
