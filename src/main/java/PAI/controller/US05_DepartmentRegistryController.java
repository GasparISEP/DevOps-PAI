package PAI.controller;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.service.department.IDepartmentService;
import org.springframework.stereotype.Component;

@Component
public class US05_DepartmentRegistryController {

    private final IDepartmentService _departmentService;

    public US05_DepartmentRegistryController(IDepartmentService departmentService){
        if(departmentService==null){
            throw new IllegalArgumentException("Department Service cannot be null.");
        }
        this._departmentService=departmentService;
    }

    public boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception{
        if (acronym == null || name == null) {
            throw new IllegalArgumentException("Acronym or name cannot be null.");
        }
            return _departmentService.registerDepartment(acronym, name);
    }
}