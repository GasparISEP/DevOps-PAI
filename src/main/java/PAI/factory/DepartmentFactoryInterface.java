package PAI.factory;
import PAI.domain.Department;

public interface DepartmentFactoryInterface {
    Department newDepartment(String acronym, String name) throws Exception;
}
