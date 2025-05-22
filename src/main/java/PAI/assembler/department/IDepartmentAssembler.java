package PAI.assembler.department;

import PAI.domain.department.Department;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.RegisterDepartmentCommand;
import PAI.dto.department.RegisterDepartmentRequest;

public interface IDepartmentAssembler {

    RegisterDepartmentCommand toRegisterDepartmentCommand(RegisterDepartmentRequest registerDepartmentRequest);

    DepartmentDTO toDTO (Department department);
}
