package PAI.controller;

import PAI.domain.Department;
import PAI.domain.DepartmentRepository;
import PAI.domain.Teacher;

import java.util.List;

public class US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController {

    private DepartmentRepository _departmentRepository;

    public US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(DepartmentRepository departmentRepository) {

        validateDepartmentRepository (departmentRepository);
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

        this._departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return _departmentRepository.getDepartmentList();
    }
}
