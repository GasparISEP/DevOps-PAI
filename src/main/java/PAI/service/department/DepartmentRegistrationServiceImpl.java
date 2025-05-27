package PAI.service.department;

import PAI.VOs.DepartmentID;
import PAI.domain.department.Department;
import PAI.domain.department.IDepartmentFactory;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.dto.department.RegisterDepartmentRequestVOs;
import PAI.exception.BusinessRuleViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
    public Department createAndSaveDepartment(RegisterDepartmentRequestVOs requestCommand) throws Exception {
    if (requestCommand.acronym() == null || requestCommand.name() == null) {
            throw new IllegalArgumentException("Acronym and name cannot be null");
        }
    Department department = departmentFactory.newDepartment(requestCommand.acronym(), requestCommand.name());
        if (department == null) {
            throw new IllegalStateException("Failed to create department");
        }
        if (departmentRepository.containsOfIdentity(department.identity())) {
            throw new BusinessRuleViolationException("Department with this identity already exists");
        }
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Iterable<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(DepartmentID departmentID) {
        return departmentRepository.findDepartmentByID(departmentID);
    }
}
