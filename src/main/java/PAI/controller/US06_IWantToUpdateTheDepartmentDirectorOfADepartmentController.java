package PAI.controller;

import PAI.domain.Department;
import PAI.repository.DepartmentRepository;
import PAI.domain.Teacher;

import java.util.List;
import java.util.Set;

public class US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController {

    private final DepartmentRepository _departmentRepository;

    public US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(DepartmentRepository departmentRepository) {

        validateDepartmentRepository (departmentRepository);
        this._departmentRepository = departmentRepository;
    }
    public Set<Department> getAllDepartments() {
        return _departmentRepository.getDepartments();
    }
    public boolean updateDepartmentDirector(Department department, Teacher teacher) {
        if (department == null || teacher == null ) {
            return false;
        }

        return _departmentRepository.updateOfDepartmentDirector(department, teacher);
    }

    private void validateDepartmentRepository(DepartmentRepository departmentRepository) {
        if (departmentRepository == null) {
            throw new IllegalArgumentException("Department Repository cannot be null!");
        }

    }
}
