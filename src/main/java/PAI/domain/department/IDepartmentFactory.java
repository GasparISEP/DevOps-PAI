package PAI.domain.department;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;

public interface IDepartmentFactory {
    Department newDepartment(DepartmentAcronym acronym, Name name) throws Exception;

    Department newDepartment(DepartmentAcronym acronym, Name name,TeacherID directorID) throws Exception;

}

