package PAI.controllerRest;

import PAI.assembler.department.IDepartmentAssembler;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.DepartmentWithDirectorDTO;
import PAI.dto.department.DepartmentWithDirectorRequest;
import PAI.dto.department.RegisterDepartmentRequest;
import PAI.dto.department.RegisterDepartmentRequestVOs;
import PAI.domain.department.Department;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.department.IDepartmentRegistrationService;
import PAI.service.department.IUpdateDepartmentDirectorService;
import PAI.VOs.TeacherID;
import PAI.VOs.DepartmentID;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentRestController {

    private final IDepartmentRegistrationService departmentRegistrationService;
    private final IDepartmentAssembler departmentAssembler;
    private final IUpdateDepartmentDirectorService updateDepartmentDirectorService;

    public DepartmentRestController(IDepartmentRegistrationService departmentRegistrationService,
                                    IDepartmentAssembler departmentAssembler, IUpdateDepartmentDirectorService updateDepartmentDirectorService) {
        this.departmentRegistrationService = departmentRegistrationService;
        this.departmentAssembler = departmentAssembler;
        this.updateDepartmentDirectorService = updateDepartmentDirectorService;
    }

    @PostMapping
    public ResponseEntity<?> registerDepartment(
            @Valid @RequestBody RegisterDepartmentRequest request) {
        try {
            RegisterDepartmentRequestVOs requestVOs = departmentAssembler.toRegisterDepartmentRequestVOs(request);
            Department department = departmentRegistrationService.createAndSaveDepartment(requestVOs);
            DepartmentDTO responseDTO = departmentAssembler.toDTO(department);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

        } catch (BusinessRuleViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        try {
            Iterable<Department> departments = departmentRegistrationService.getAllDepartments();
            Iterable<DepartmentDTO> departmentsDTOs = departmentAssembler.toDTOs(departments);
            return ResponseEntity.ok(departmentsDTOs);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }
}