package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    private List<Department> departments;

    //constructor
    public DepartmentRepository() {
        departments = new ArrayList<>();
    }

    public boolean registerDepartment(String acronym, String name) throws Exception {

        Department department = new Department(acronym,name);

        compareDepartmentAcronymAndNameInList(department);
        departments.add(department);

        return true;
    }

    private void compareDepartmentAcronymAndNameInList(Department department) {
        for (Department existingDepartment : departments) {
            if (department.hasSameAcronym(existingDepartment)) {
                throw new IllegalArgumentException("Department with that acronym already exists.");
            }
            if (department.hasSameName(existingDepartment)) {
                throw new IllegalArgumentException("Department with that name already exists.");
            }
        }
    }
}
