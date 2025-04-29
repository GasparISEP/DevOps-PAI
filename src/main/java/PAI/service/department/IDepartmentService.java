package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;

public interface IDepartmentService {
     boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception;

     boolean updateOfDepartmentDirector(DepartmentID departmentID, TeacherID furtherDirectorID);

     boolean containsOfIdentity(DepartmentID id);
}
