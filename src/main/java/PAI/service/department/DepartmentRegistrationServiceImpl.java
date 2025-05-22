package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.department.Department;
import PAI.domain.department.IDepartmentFactory;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.exception.BusinessRuleViolationException;

public class DepartmentRegistrationServiceImpl implements IDepartmentRegistrationService {

    private final IDepartmentFactory departmentFactory;
    private final IDepartmentRepository departmentRepository;

    public DepartmentRegistrationServiceImpl(IDepartmentFactory departmentFactory, IDepartmentRepository departmentRepository) {
        if (departmentFactory == null) throw new IllegalArgumentException("DepartmentFactory cannot be null");
        if (departmentRepository == null) throw new IllegalArgumentException("Department Repository cannot be null");

        this.departmentFactory = departmentFactory;
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Department createAndSaveDepartment(DepartmentAcronym acronym, Name name) throws Exception {
    if (acronym == null || name == null) {
            throw new IllegalArgumentException("Acronym and name cannot be null");
        }
    Department department = departmentFactory.newDepartment(acronym, name);
        if (department == null) {
            throw new IllegalStateException("Failed to create department");
        }
        if (departmentRepository.containsOfIdentity(department.identity())) {
            throw new BusinessRuleViolationException("Department with this identity already exists");
        }
        departmentRepository.save(department);
        return department;
    }
}
