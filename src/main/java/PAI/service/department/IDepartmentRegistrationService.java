package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.department.Department;

public interface IDepartmentRegistrationService {

    Department createAndSaveDepartment(DepartmentAcronym acronym, Name name) throws Exception;
}
