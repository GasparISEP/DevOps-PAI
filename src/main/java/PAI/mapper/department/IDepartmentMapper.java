package PAI.mapper.department;

import PAI.domain.Department;
import PAI.factory.IDepartmentFactory;
import PAI.persistence.datamodel.department.DepartmentDataModel;

public interface IDepartmentMapper {
    DepartmentDataModel toDataModel(Department department);
    Department toDomain(DepartmentDataModel departmentDataModel) throws Exception;
}
