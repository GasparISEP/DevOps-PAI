package PAI.repository;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.ddd.IRepository;
import PAI.domain.Department;

import java.util.Set;

public interface IDepartmentRepository extends IRepository<DepartmentID, Department> {

    boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception;

    Set<DepartmentID> getDepartmentIDs();
}