package PAI.assembler.department;

import PAI.VOs.TeacherAcronym;
import PAI.domain.department.Department;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.DepartmentWithDirectorDTO;
import PAI.dto.department.RegisterDepartmentRequestVOs;
import PAI.dto.department.RegisterDepartmentRequest;

public interface IDepartmentAssembler {

    RegisterDepartmentRequestVOs toRegisterDepartmentRequestVOs(RegisterDepartmentRequest registerDepartmentRequest);

    DepartmentDTO toDTO(Department department);

    Iterable<DepartmentDTO> toDTOs(Iterable<Department> departments);

    DepartmentWithDirectorDTO toDWDDTO(Department department);

    Iterable<DepartmentWithDirectorDTO> toDWDDTOs(Iterable<Department> departments);

    Department updateDepartmentWithDirector(Department department, TeacherAcronym teacherID);
}

