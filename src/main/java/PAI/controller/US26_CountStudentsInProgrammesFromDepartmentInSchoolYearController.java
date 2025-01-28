package PAI.controller;


import PAI.domain.*;
import PAI.controller.*;

public class US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController {
    private ProgrammeEditionEnrollmentRepo _PEERepo;
    private SchoolYearRepository _schoolYearRepository;
    private DepartmentRepository _departmentRepository;

    public US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(
            ProgrammeEditionEnrollmentRepo PEERepo,
            SchoolYearRepository schoolYearRepository,
            DepartmentRepository departmentRepository
    ) {
        _PEERepo=PEERepo;
        _schoolYearRepository=schoolYearRepository;
        _departmentRepository=departmentRepository;
    }

    public int countStudentsInProgrammesFromDepartmentInSchoolYear(Department department, SchoolYear schoolYear) throws Exception{
        if(_PEERepo==null || _schoolYearRepository==null || _departmentRepository==null){
            throw new Exception("Repositories cannot be null.");
        }
        if(department==null || schoolYear==null){
            throw new Exception("Department or SchoolYear cannot be null");
        }
        if(!_schoolYearRepository.schoolYearExists(schoolYear)){
            throw new Exception("SchoolYear does not exist.");
        }
        if(!_departmentRepository.departmentExists(department)){
            throw new Exception("Department does not exist.");
        }
        return _PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(department,schoolYear);
    }
}
