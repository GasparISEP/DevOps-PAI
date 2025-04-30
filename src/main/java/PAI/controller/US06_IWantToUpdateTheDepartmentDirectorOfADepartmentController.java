package PAI.controller;
import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.service.department.IDepartmentService;
import org.springframework.stereotype.Controller;

@Controller
public class US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController {

    private final IDepartmentService _departmentService;

    public US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(IDepartmentService departmentService) {

        validateDepartmentService(departmentService);

        this._departmentService = departmentService;
    }
    public boolean updateOfDepartmentDirector(DepartmentID departmentId, TeacherID teacherId) {
       return _departmentService.updateOfDepartmentDirector(departmentId, teacherId);
    }

    private void validateDepartmentService(IDepartmentService departmentService) {
        if (departmentService == null) {
            throw new IllegalArgumentException("Department Service cannot be null!");
        }
    }
}


