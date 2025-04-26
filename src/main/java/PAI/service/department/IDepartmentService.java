package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;

public interface IDepartmentService {
     boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception;
}
