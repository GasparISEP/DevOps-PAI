package PAI.persistence.springdata.Department;

import PAI.persistence.datamodel.department.DepartmentDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepositorySpringData extends JpaRepository<DepartmentDataModel, DepartmentIDDataModel> {
}
