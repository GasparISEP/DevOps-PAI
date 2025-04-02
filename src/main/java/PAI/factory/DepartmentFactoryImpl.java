package PAI.factory;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.Department;

public class DepartmentFactoryImpl implements IDepartmentFactory {

    public Department newDepartment(DepartmentAcronym acronym, Name name) throws Exception{
        return new Department (acronym, name);
    }
}