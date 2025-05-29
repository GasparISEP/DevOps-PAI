package PAI.controllerRest;

import PAI.VOs.DepartmentAcronym;
import PAI.assembler.department.IDepartmentAssembler;
import PAI.dto.department.*;
import PAI.assembler.department.IDepartmentHateoasAssembler;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.RegisterDepartmentRequest;
import PAI.dto.department.RegisterDepartmentRequestVOs;
import PAI.domain.department.Department;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.department.IDepartmentRegistrationService;
import PAI.service.department.IUpdateDepartmentDirectorService;
import PAI.VOs.DepartmentID;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import PAI.VOs.TeacherID;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentRestController {

    private final IDepartmentRegistrationService departmentRegistrationService;
    private final IDepartmentAssembler departmentAssembler;
    private final IUpdateDepartmentDirectorService updateDepartmentDirectorService;
    private final IDepartmentHateoasAssembler departmentHateoasAssembler;

    public DepartmentRestController(IDepartmentRegistrationService departmentRegistrationService,
                                    IDepartmentAssembler departmentAssembler, IUpdateDepartmentDirectorService updateDepartmentDirectorService, IDepartmentHateoasAssembler departmentHateoasAssembler) {
        this.departmentRegistrationService = departmentRegistrationService;
        this.departmentAssembler = departmentAssembler;
        this.updateDepartmentDirectorService = updateDepartmentDirectorService;
        this.departmentHateoasAssembler = departmentHateoasAssembler;
    }

    @PostMapping
    public ResponseEntity<?> registerDepartment(
            @Valid @RequestBody RegisterDepartmentRequest request) {
        try {
            RegisterDepartmentRequestVOs requestVOs = departmentAssembler.toRegisterDepartmentRequestVOs(request);
            Department department = departmentRegistrationService.createAndSaveDepartment(requestVOs);
            DepartmentDTO responseDTO = departmentAssembler.toDTO(department);
            return ResponseEntity.status(HttpStatus.CREATED).body(departmentHateoasAssembler.toModel(responseDTO));

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
            Iterable<DepartmentWithDirectorDTO> departmentsDTOs = departmentAssembler.toDWDDTOs(departments);
            return ResponseEntity.ok(departmentsDTOs);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") String id) {
        try {
            DepartmentID departmentId = departmentAssembler.fromStringToDepartmentID(id);

            Optional<Department> department = departmentRegistrationService.getDepartmentById(departmentId);
            if (department.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found");
            }

            DepartmentDTO responseDTO = departmentAssembler.toDTO(department.get());
            return ResponseEntity.ok(responseDTO);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }
    @PatchMapping
    public ResponseEntity<?> updateDepartmentDirector(
            @Valid @RequestBody DepartmentWithDirectorRequest request) {
        try {
            DepartmentWithDirectorCommand command = departmentAssembler.fromRequestToCommand(request);
            DepartmentID departmentID = command.department();
            TeacherID teacherID = command.director();
            DepartmentWithDirectorDTO dto = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);
            return new ResponseEntity<>(dto, HttpStatus.OK); // 200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //400
        } catch (BusinessRuleViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); //409
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred."); //500
        }
    }
}