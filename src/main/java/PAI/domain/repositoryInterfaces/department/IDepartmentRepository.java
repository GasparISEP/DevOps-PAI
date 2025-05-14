package PAI.domain.repositoryInterfaces.department;
import PAI.VOs.DepartmentID;
import PAI.ddd.IRepository;
import PAI.domain.department.Department;

import java.util.Optional;
import java.util.Set;

public interface IDepartmentRepository extends IRepository<DepartmentID, Department> {

    Set<DepartmentID> getDepartmentIDs();

    Optional<Department> findDepartmentByID(DepartmentID departmentID);

}