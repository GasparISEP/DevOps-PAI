package PAI.domain.department;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import org.springframework.stereotype.Component;

@Component
public class DepartmentFactoryImpl implements IDepartmentFactory {

    @Override
    public Department newDepartment(DepartmentAcronym acronym, Name name) throws Exception {
        return new Department(acronym, name);
    }

    @Override
    public Department newDepartment(DepartmentAcronym acronym, Name name,TeacherID directorID) throws Exception {
        return new Department(acronym, name, directorID);
    }
}