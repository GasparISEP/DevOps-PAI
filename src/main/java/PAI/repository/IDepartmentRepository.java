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

    boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception;

    Set<DepartmentID> getDepartmentIDs();

    Optional<Department> findDepartmentByID(DepartmentID departmentID);

    boolean departmentExists(DepartmentID departmentID);

    boolean updateOfDepartmentDirector(DepartmentID departmentId, TeacherID teacherId);
}