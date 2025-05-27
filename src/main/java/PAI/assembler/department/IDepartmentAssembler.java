package PAI.assembler.department;

import PAI.VOs.TeacherAcronym;
import PAI.domain.department.Department;
import PAI.dto.department.*;

public interface IDepartmentAssembler {

    RegisterDepartmentCommand toRegisterDepartmentCommand(RegisterDepartmentRequest registerDepartmentRequest);

    DepartmentDTO toDTO(Department department);

    Iterable<DepartmentDTO> toDTOs(Iterable<Department> departments);

    DepartmentWithDirectorDTO toDWDDTO(Department department);

    Iterable<DepartmentWithDirectorDTO> toDWDDTOs(Iterable<Department> departments);

    DepartmentWithDirectorCommand fromRequestToCommand(DepartmentWithDirectorRequest request);
}

