package PAI.factory;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.Department;

public interface IDepartmentFactory {
    Department newDepartment(DepartmentAcronym acronym, Name name) throws Exception;
}

