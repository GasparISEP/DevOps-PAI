package PAI.domain.department;
import PAI.VOs.DepartmentID;
import PAI.ddd.IRepository;

import java.util.Optional;
import java.util.Set;

public interface IDepartmentRepository extends IRepository<DepartmentID, Department> {

    Set<DepartmentID> getDepartmentIDs();

    Optional<Department> findDepartmentByID(DepartmentID departmentID);

}