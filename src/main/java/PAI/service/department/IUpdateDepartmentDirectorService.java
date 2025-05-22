package PAI.service.department;

import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.teacher.Teacher;

public interface IUpdateDepartmentDirectorService {

    Department updateDirector(DepartmentID departmentID, TeacherID teacherID) throws Exception;

    Iterable<Department> listDepartments();

    Iterable<Teacher> listTeachersByDepartment(DepartmentID departmentID);
}