package PAI.factory;
import PAI.domain.Department;
import java.util.Set;

public interface IDepartmentListFactory {
    Set<Department> newDepartmentList ();
}
