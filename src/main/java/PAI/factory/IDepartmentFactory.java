package PAI.factory;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import PAI.domain.Department;

public interface IDepartmentFactory {
    Department newDepartment(DepartmentAcronym acronym, Name name) throws Exception;

    Department newDepartment(DepartmentAcronym acronym, Name name,TeacherID directorID) throws Exception;

}

