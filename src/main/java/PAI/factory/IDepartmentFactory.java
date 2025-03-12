package PAI.factory;
import PAI.domain.Department;

public interface IDepartmentFactory {
    Department newDepartment(String acronym, String name) throws Exception;
}
