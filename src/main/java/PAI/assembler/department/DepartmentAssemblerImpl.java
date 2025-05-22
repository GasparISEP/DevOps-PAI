package PAI.assembler.department;


import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.department.Department;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.RegisterDepartmentCommand;
import PAI.dto.department.RegisterDepartmentRequest;
import org.springframework.stereotype.Component;

@Component
public class DepartmentAssemblerImpl implements IDepartmentAssembler {

    @Override
    public RegisterDepartmentCommand toRegisterDepartmentCommand(RegisterDepartmentRequest registerDepartmentRequest) {
        if (registerDepartmentRequest == null) {
            throw new IllegalArgumentException("RegisterDepartmentRequest cannot be null");
        }
        Name name = new Name(registerDepartmentRequest.name());
        DepartmentAcronym acronym = new DepartmentAcronym(registerDepartmentRequest.acronym());

        return new RegisterDepartmentCommand(name, acronym);
    }

    @Override
    public DepartmentDTO toDTO (Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        return new DepartmentDTO(
                department.identity().toString(),
                department.getName().toString(),
                department.getAcronym().toString(),
                department.getDirectorID().toString()
        );
    }
}
