package PAI.persistence.mem.department;
import PAI.domain.department.Department;

import java.util.Set;

public interface IDepartmentListFactory {
    Set<Department> newDepartmentList ();
}
