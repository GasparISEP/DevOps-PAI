package PAI.mapper.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.Department;
import PAI.factory.IDepartmentFactory;
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
        return  new DepartmentDataModel(department.getName().getName(),department.getAcronym().getAcronym());
    }
    @Override
       public Department toDomain(DepartmentDataModel departmentDataModel) throws Exception {
        if (departmentDataModel == null) {
            throw new IllegalArgumentException("departmentDataModel Cannot be null");
        }
        DepartmentAcronym acronym = new DepartmentAcronym(departmentDataModel.getAcronym());
        Name name = new Name(departmentDataModel.getName());

        return  departmentFactory.newDepartment(acronym,name);
    }
}
