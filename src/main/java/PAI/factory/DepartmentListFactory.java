package PAI.factory;
import PAI.domain.Department;
import java.util.HashSet;
import java.util.Set;

public class DepartmentListFactory implements DepartmentListFactoryInterface {
    public Set<Department> newDepartmentList() { return new HashSet<>();
    }
}
