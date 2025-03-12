package PAI.repository;
import PAI.domain.Department;
import PAI.domain.Teacher;
import java.util.Set;
import PAI.factory.IDepartmentFactory;
import PAI.factory.IDepartmentListFactory;

public class DepartmentRepository {

    private final Set<Department> _departments;
    private final IDepartmentFactory _departmentFactory;

    //constructor
    public DepartmentRepository(IDepartmentFactory IDepartmentFactory, IDepartmentListFactory IDepartmentListFactory) {
        _departmentFactory = IDepartmentFactory;
        _departments = IDepartmentListFactory.newDepartmentList();
    }

    public boolean registerDepartment(String acronym, String name) throws Exception {
        Department newDepartment = _departmentFactory.newDepartment(acronym,name);

        boolean isDepartmentRegistered = _departments.add(newDepartment);

        if (!isDepartmentRegistered) {
            return false;
        }
        return true;
    }

    // Method to get the list of Departments
    public Set<Department> getDepartments() {
        if (_departments.isEmpty()){
            throw new IllegalStateException("Department list is empty.");
        }
        return _departments;
    }

    public boolean departmentExists (Department department){
        return department != null && _departments.contains(department);
    }

    public boolean updateOfDepartmentDirector(Department department, Teacher furtherDirector) {
        if(furtherDirector!=null && furtherDirector.isInDepartment(department)) {
            department.changeDirector(furtherDirector);
            return true;
        }
            return false;
    }
}
