package PAI.mapper.department;

import PAI.VOs.DepartmentID;
import PAI.persistence.datamodel.DepartmentIDDataModel;

public interface IDepartmentIDMapper {

    DepartmentIDDataModel toDataModel(DepartmentID departmentID);
    DepartmentID toDomainModel(DepartmentIDDataModel departmentIDDataModel);
}
