package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    private List<Department> _departments;

    //constructor
    public DepartmentRepository() {
        _departments = new ArrayList<>();
    }

    public boolean registerDepartment(String acronym, String name) throws Exception {

        Department department = new Department(acronym,name);

        compareDepartmentAcronymAndNameInList(department);
        _departments.add(department);

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

    public boolean updateOfDepartmentDirector(Department department, Teacher teacher){
        department.changeDirector(teacher);
        return true;
    }
}
