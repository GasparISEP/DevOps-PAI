package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    private List<Department> departments;

    //constructor
    public DepartmentRepository() {
        departments = new ArrayList<>();
    }


    public Department createDepartment (String acronym, String name) throws Exception {
        return new Department(acronym,name);

    }

    public void registerDepartment(Department department) throws IllegalArgumentException {
        if (department == null)
            throw new IllegalArgumentException("Department cannot be null.");

        for (Department existingDepartment : departments) {
            if (department.hasSameAcronym(existingDepartment)) {
                throw new IllegalArgumentException("Department with that acronym already exists.");
            }
            if (department.hasSameName(existingDepartment)) {
                throw new IllegalArgumentException("Department with that name already exists.");
            }
        }
        departments.add(department);
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
