package PAI.mapper.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.domain.Department;
import PAI.factory.IDepartmentFactory;
import PAI.persistence.datamodel.TeacherIDDataModel;
import PAI.persistence.datamodel.department.DepartmentDataModel;

import org.springframework.stereotype.Component;
@Component
public class DepartmentMapperImpl implements IDepartmentMapper {
    private final IDepartmentFactory departmentFactory;
    public DepartmentMapperImpl(IDepartmentFactory departmentFactory) {
        this.departmentFactory = departmentFactory;
    }
    @Override
    public DepartmentDataModel toDataModel(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("department Cannot be null");
        }
        String name = department.getName().getName();
        String acronym = department.getAcronym().getAcronym();
        if(department.getDirectorID() != null) {
            String teacherAcronym = department.getDirectorID().getTeacherAcronym().getAcronym();
            TeacherIDDataModel directorID = new TeacherIDDataModel(teacherAcronym);
            return new DepartmentDataModel(name,acronym,directorID);
        }
        return new DepartmentDataModel(name,acronym);
    }
    @Override
    public Department toDomain(DepartmentDataModel departmentDataModel) throws Exception {
        if (departmentDataModel == null) {
            throw new IllegalArgumentException("departmentDataModel Cannot be null");
        }
        DepartmentAcronym acronym = new DepartmentAcronym(departmentDataModel.getAcronym());
        Name name = new Name(departmentDataModel.getName());
        if(departmentDataModel.getDirectorId() != null) {
            TeacherAcronym teacherAcronym = new TeacherAcronym(departmentDataModel.getDirectorId().getTeacherAcronym());
            TeacherID directorID = new TeacherID(teacherAcronym);
            return departmentFactory.newDepartment(acronym, name, directorID);
        }
        return  departmentFactory.newDepartment(acronym,name);
    }
}
