package PAI.repository;
import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.Department;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import PAI.factory.IDepartmentFactory;
import PAI.factory.IDepartmentListFactory;

public class DepartmentRepositoryImpl implements IDepartmentRepository{

    private final Set<Department> _departments;
    private final IDepartmentFactory _departmentFactory;

    //constructor
    public DepartmentRepositoryImpl(IDepartmentFactory iDepartmentFactory, IDepartmentListFactory iDepartmentListFactory) {
        _departmentFactory = iDepartmentFactory;
        _departments = iDepartmentListFactory.newDepartmentList();
    }

    // Method to get the list of Departments
    public Set<DepartmentID> getDepartmentIDs() {
        if (_departments.isEmpty()) {
            throw new IllegalStateException("Department list is empty.");
        }
        return _departments.stream()
                .map(Department::identity)
                .collect(Collectors.toSet());
    }

    public boolean departmentExists(DepartmentID departmentID) {
        if (departmentID == null) {
            return false;
        }
        return findDepartmentByID(departmentID).isPresent();
    }

    public Optional<Department> findDepartmentByID(DepartmentID departmentID) {
        for (Department department : _departments) {
            if (department.getDepartmentID().equals(departmentID)) {
                return Optional.of(department);
            }
        }
        return Optional.empty();
    }

    //US06
    public boolean updateOfDepartmentDirector(DepartmentID departmentID, TeacherID furtherDirectorID) {
        if (departmentID == null || furtherDirectorID == null) {
            return false;
        }

        Optional<Department> departmentOptional = ofIdentity(departmentID);
        if (!departmentOptional.isPresent()) {
            return false;
        }

        Department department = departmentOptional.get();
        return department.changeDirector(furtherDirectorID);
    }


    @Override
    public Department save(Department entity) {
        _departments.add(entity);
        return entity;
    }

    @Override
    public Iterable<Department> findAll() {
        return new HashSet<>(_departments);
    }

    @Override
    public Optional<Department> ofIdentity(DepartmentID id) {
        return _departments.stream()
                .filter(department -> department.identity().equals(id))
                .findFirst();
    }

    @Override
    public boolean containsOfIdentity(DepartmentID id) {
        return ofIdentity(id).isPresent();
    }

}
