package PAI.domain;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    private List<Department> _departments;
    private final DepartmentFactory _departmentFactory;

    //constructor
    public DepartmentRepository(DepartmentFactory departmentFactory) {
        _departmentFactory = departmentFactory;
        _departments = new ArrayList<>();
    }

    public boolean registerDepartment(String acronym, String name) throws Exception {

        Department newDepartment = _departmentFactory.newDepartment(acronym,name);
        compareDepartmentAcronymAndNameInList(newDepartment);
        _departments.add(newDepartment);

        return true;
    }

    private void compareDepartmentAcronymAndNameInList(Department department) {
        for (Department existingDepartment : _departments) {
            if (department.hasSameAcronym(existingDepartment)) {
                throw new IllegalArgumentException("Department with that acronym already exists.");
            }
            if (department.hasSameName(existingDepartment)) {
                throw new IllegalArgumentException("Department with that name already exists.");
            }
        }
    }

    // Method to get the list of Departments
    public List<Department> getDepartmentsList() {
        if (_departments.isEmpty()){
            throw new IllegalStateException("Department list is empty.");
        }
        return _departments;
    }

    public boolean departmentExists (Department department){
        if(department==null){
            return false;
        }
        for(Department existingDepartment : _departments){
            if(department.equals(existingDepartment)){
                return true;
            }
        }
        return false;
    }

    public boolean updateOfDepartmentDirector(Department department, Teacher furtherDirector) {
        if(furtherDirector!=null && furtherDirector.isInDepartment(department)) {
            department.changeDirector(furtherDirector);
            return true;
        }
        return false;
    }
}
