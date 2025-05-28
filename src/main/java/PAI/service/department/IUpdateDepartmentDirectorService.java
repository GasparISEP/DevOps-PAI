package PAI.service.department;

import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.teacher.Teacher;
import PAI.dto.department.DepartmentWithDirectorDTO;

import java.util.Set;

public interface IUpdateDepartmentDirectorService {

    DepartmentWithDirectorDTO updateDirector(DepartmentID departmentID, TeacherID teacherID) throws Exception;
}