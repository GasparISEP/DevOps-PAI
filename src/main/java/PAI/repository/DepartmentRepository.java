package PAI.repository;
import PAI.domain.Department;
import PAI.domain.Teacher;
import PAI.factory.DepartmentFactory;

import java.util.HashSet;
import java.util.Set;

public class DepartmentRepository {

    private Set<Department> _departments;
    private final DepartmentFactory _departmentFactory;

    //constructor
    public DepartmentRepository(DepartmentFactory departmentFactory) {
        _departmentFactory = departmentFactory;
        _departments = new HashSet<>();
    }

    public boolean registerDepartment(String acronym, String name) throws Exception {
        Department newDepartment = _departmentFactory.newDepartment(acronym, name);

        for (Department existingDepartment : _departments) {
            if (existingDepartment.hasSameAcronym(newDepartment)) {
                throw new IllegalArgumentException("Department with that acronym already exists.");
            }
            if (existingDepartment.hasSameName(newDepartment)) {
                throw new IllegalArgumentException("Department with that name already exists.");
            }
        }
        return _departments.add(newDepartment);
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
