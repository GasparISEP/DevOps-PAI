package PAI.factory;

import PAI.domain.Department;

public class DepartmentFactory {

    public Department newDepartment(String acronym, String name) throws Exception{
        return new Department (acronym, name);
    }
}
