package PAI.repository;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import PAI.ddd.IRepository;
import PAI.domain.Department;

import java.util.Optional;
import java.util.Set;

public interface IDepartmentRepository extends IRepository<DepartmentID, Department> {

    Set<DepartmentID> getDepartmentIDs();

    Optional<Department> findDepartmentByID(DepartmentID departmentID);

    boolean updateOfDepartmentDirector(DepartmentID departmentId, TeacherID teacherId);
}