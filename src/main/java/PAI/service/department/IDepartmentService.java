package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import PAI.domain.Department;

import java.util.Set;

public interface IDepartmentService {
     boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception;

     boolean updateOfDepartmentDirector(DepartmentID departmentID, TeacherID furtherDirectorID) throws Exception;

     boolean containsOfIdentity(DepartmentID id);

     Iterable<Department> findAll();

     boolean departmentExists(DepartmentID id);

     Set<DepartmentID> getDepartmentIDs();
}
