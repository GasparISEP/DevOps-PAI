package PAI.service.department;

import PAI.domain.department.Department;
import PAI.dto.department.RegisterDepartmentRequestVOs;

public interface IDepartmentRegistrationService {

    Department createAndSaveDepartment(RegisterDepartmentRequestVOs requestCommand) throws Exception;

    Iterable<Department> getAllDepartments();
}
