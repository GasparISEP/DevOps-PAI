package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.department.Department;
import PAI.dto.department.RegisterDepartmentCommand;

public interface IDepartmentRegistrationService {

    Department createAndSaveDepartment(RegisterDepartmentCommand requestCommand) throws Exception;
}
