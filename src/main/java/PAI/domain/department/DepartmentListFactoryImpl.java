package PAI.domain.department;

import java.util.HashSet;
import java.util.Set;

public class DepartmentListFactoryImpl implements IDepartmentListFactory {
    public Set<Department> newDepartmentList() { return new HashSet<>();
    }
}
