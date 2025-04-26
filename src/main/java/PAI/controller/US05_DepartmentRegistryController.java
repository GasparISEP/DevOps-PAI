package PAI.controller;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.repository.IDepartmentRepository;

public class US05_DepartmentRegistryController {

    private final IDepartmentRepository _departmentRepo;

    public US05_DepartmentRegistryController(IDepartmentRepository departmentRepo){
        if(departmentRepo==null){
            throw new IllegalArgumentException("Department Repository cannot be null.");
        }
        this._departmentRepo=departmentRepo;
    }

//    public boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception{
//        if(acronym==null || name==null){
//            throw new IllegalArgumentException("Acronym or name cannot be null.");
//        }
//        _departmentRepo.registerDepartment(acronym,name);
//        return true;
//    }
}