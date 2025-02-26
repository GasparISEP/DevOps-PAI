package PAI.domain;

public class DepartmentFactory {

    public Department newDepartment(String acronym, String name) throws Exception{
        return new Department (acronym, name);
    }
}
