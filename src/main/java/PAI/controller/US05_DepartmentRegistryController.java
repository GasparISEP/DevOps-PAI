package PAI.controller;
import PAI.domain.DepartmentRepository;

public class US05_DepartmentRegistryController {

    private DepartmentRepository _departmentRepo;

    public US05_DepartmentRegistryController(DepartmentRepository departmentRepo){
        if(departmentRepo==null){
            throw new IllegalArgumentException("Department Repository cannot be null.");
        }
        this._departmentRepo=departmentRepo;
    }

    public boolean registerDepartment(String acronym, String name) throws Exception{
        if(acronym==null || name==null){
            throw new IllegalArgumentException("Acronym or name cannot be null.");
        }
        _departmentRepo.registerDepartment(acronym,name);
        return true;
    }
}