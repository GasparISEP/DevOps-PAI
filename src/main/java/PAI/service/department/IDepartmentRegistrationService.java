package PAI.service.department;

import PAI.domain.department.Department;
import PAI.dto.department.RegisterDepartmentCommand;

public interface IDepartmentRegistrationService {

    Department createAndSaveDepartment(RegisterDepartmentCommand requestCommand) throws Exception;

    Iterable<Department> getAllDepartments();
}
