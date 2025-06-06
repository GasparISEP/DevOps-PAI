package PAI.mapper.department;

import PAI.VOs.DepartmentAcronym;

import PAI.VOs.Name;
import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.department.IDepartmentFactory;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import PAI.persistence.datamodel.department.DepartmentDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;

import org.springframework.stereotype.Component;
@Component
public class DepartmentMapperImpl implements IDepartmentMapper {
    private final IDepartmentFactory departmentFactory;
    public DepartmentMapperImpl(IDepartmentFactory departmentFactory) {
        this.departmentFactory = departmentFactory;
    }

    public DepartmentDataModel toDataModel(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("department Cannot be null");
        }

        DepartmentDataModel dataModel = new DepartmentDataModel();
        dataModel.setId(new DepartmentIDDataModel(department.identity().getAcronym().getAcronym()));
        dataModel.setName(department.getName().getName());
        dataModel.setAcronym(department.getAcronym().getAcronym());

        if (department.getDirectorID() != null) {
            String teacherAcronym = department.getDirectorID().getTeacherAcronym().getAcronym();
            TeacherIDDataModel directorID = new TeacherIDDataModel(teacherAcronym);
            dataModel.setDirectorId(directorID);
        } else {
            dataModel.setDirectorId(null);
        }

        return dataModel;
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
