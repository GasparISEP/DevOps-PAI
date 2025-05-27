package PAI.service.department;

import PAI.VOs.DepartmentID;
import PAI.domain.department.Department;
import PAI.dto.department.RegisterDepartmentRequestVOs;

import java.util.Optional;

public interface IDepartmentRegistrationService {

    Department createAndSaveDepartment(RegisterDepartmentRequestVOs requestCommand) throws Exception;

    Iterable<Department> getAllDepartments();

    Optional<Department> getDepartmentById (DepartmentID departmentID);
}
