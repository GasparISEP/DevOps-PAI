package PAI.service.department;

import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.teacher.Teacher;

import java.util.Set;

public interface IUpdateDepartmentDirectorService {

    Department updateDirector(DepartmentID departmentID, TeacherID teacherID) throws Exception;

    Iterable<Teacher> listTeachersByDepartment(DepartmentID departmentID);

    Set<DepartmentID> getDepartmentIDs();
}