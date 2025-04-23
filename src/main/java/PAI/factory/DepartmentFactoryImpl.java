package PAI.factory;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentFactoryImpl implements IDepartmentFactory {

    @Override
    public Department newDepartment(DepartmentAcronym acronym, Name name) throws Exception {
        return new Department(acronym, name);
    }
}