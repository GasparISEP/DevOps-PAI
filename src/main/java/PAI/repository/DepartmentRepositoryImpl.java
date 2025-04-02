package PAI.repository;
import PAI.VOs.DepartmentID;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.Department;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import PAI.factory.IDepartmentFactory;
import PAI.factory.IDepartmentListFactory;

public class DepartmentRepository {

    private final Set<Department> _departments;
    private final IDepartmentFactory _departmentFactory;

    //constructor
    public DepartmentRepository(IDepartmentFactory IDepartmentFactory, IDepartmentListFactory IDepartmentListFactory) {
        _departmentFactory = IDepartmentFactory;
        _departments = IDepartmentListFactory.newDepartmentList();
    }

    public boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception {

        Department newDepartment = _departmentFactory.newDepartment(acronym, name);

        boolean isDepartmentRegistered = _departments.add(newDepartment);

        if (!isDepartmentRegistered) {
            return false;
        }
        return true;
    }

    // Method to get the list of Departments
    public Set<DepartmentID> getDepartmentIDs() {
        if (_departments.isEmpty()) {
            throw new IllegalStateException("Department list is empty.");
        }
        return _departments.stream()
                .map(Department::getDepartmentID)
                .collect(Collectors.toSet());
    }

    public boolean departmentExists(DepartmentID departmentID) {
        if (departmentID == null) {
            return false;
        }
        return findDepartementByID(departmentID).isPresent();
    }

    public Optional<Department> findDepartementByID(DepartmentID departmentID) {
        for (Department department : _departments) {
            if (department.getDepartmentID().equals(departmentID)) {
                return Optional.of(department);
            }
        }
        return Optional.empty();
    }

//    public boolean updateOfDepartmentDirector(DepartmentID departmentId, TeacherID furtherDirectorId) {
//        if (furtherDirectorId.getTeacherById.isInDepartment(department.getDepartmentById(departmentId))) {
//            departmentId.changeDirector(furtherDirectorId.identity());
//        }
//        return false;
//        }

}