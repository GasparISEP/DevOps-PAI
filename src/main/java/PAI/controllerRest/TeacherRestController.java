package PAI.controllerRest;


import PAI.VOs.*;
import PAI.assembler.teacher.ITeacherAssembler;
import PAI.assembler.teacher.ITeacherHateoasAssembler;
import PAI.assembler.teacher.ITeacherWithRelevantDataHateoasAssembler;
import PAI.assembler.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.assembler.teacherCareerProgression.IUpdateTeacherCategoryHateoasAssembler;
import PAI.assembler.teacherCareerProgression.IUpdateTeacherWorkingPercentageHateoasAssembler;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacher.*;
import PAI.dto.teacherCareerProgression.*;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.teacher.ITeacherRegistrationService;
import PAI.service.teacher.ITeacherWithRelevantDataService;
import PAI.service.teacherCareerProgression.ICreateTeacherCareerProgressionService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static PAI.utils.ValidationUtils.validateNotNull;


import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherRestController {

    private final ITeacherRegistrationService teacherRegistrationService;
    private final ITeacherAssembler teacherAssembler;
    private final ITeacherHateoasAssembler teacherHateoasAssembler;
    private final ICreateTeacherCareerProgressionService careerService;
    private final ITeacherCareerProgressionAssembler careerAssembler;
    private final ITeacherWithRelevantDataService teacherWithRelevantDataService;
    private final TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler;
    private final IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler;
    private final IUpdateTeacherCategoryHateoasAssembler updateTeacherCategoryHateoasAssemblerInterface;
    private final ITeacherWithRelevantDataHateoasAssembler iTeacherWithRelevantDataHateoasAssembler;

    public TeacherRestController(ITeacherRegistrationService teacherService, ITeacherAssembler teacherAssembler,
                                 ITeacherHateoasAssembler teacherHateoasAssembler, ICreateTeacherCareerProgressionService careerService,
                                 ITeacherCareerProgressionAssembler careerAssembler, ITeacherWithRelevantDataService teacherWithRelevantDataService,
                                 TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler,
                                 IUpdateTeacherWorkingPercentageHateoasAssembler updateTeacherWorkingPercentageHateoasAssembler,
                                 IUpdateTeacherCategoryHateoasAssembler updateTeacherCategoryHateoasAssemblerInterface,
                                 ITeacherWithRelevantDataHateoasAssembler iTeacherWithRelevantDataHateoasAssembler) {
        this.teacherRegistrationService = validateNotNull (teacherService, "Teacher Registration Service Interface");
        this.teacherAssembler = validateNotNull (teacherAssembler, "Teacher Assembler Interface");
        this.teacherHateoasAssembler = validateNotNull(teacherHateoasAssembler, "Teacher Hateoas Assembler Interface");
        this.careerService = validateNotNull(careerService,  "Teacher Career Progression Service Interface");
        this.careerAssembler = validateNotNull(careerAssembler,  "Teacher Career Progression Assembler Interface");
        this.teacherWithRelevantDataService = validateNotNull(teacherWithRelevantDataService,  "Teacher With Relevant Data Service Interface");
        this.teacherWithRelevantDataAssembler =
                validateNotNull(teacherWithRelevantDataAssembler,  "Teacher With Relevant Data Assembler Interface");
        this.updateTeacherWorkingPercentageHateoasAssembler =
                validateNotNull(updateTeacherWorkingPercentageHateoasAssembler,  "Update Teacher Working Percentage Hateoas Assembler Interface");
        this.updateTeacherCategoryHateoasAssemblerInterface = validateNotNull(updateTeacherCategoryHateoasAssemblerInterface, "Update Teacher Category Hateoas Assembler Interface");
        this.iTeacherWithRelevantDataHateoasAssembler = validateNotNull(iTeacherWithRelevantDataHateoasAssembler, "Teacher With Relevant Data Hateoas Assembler Interface");
    }

    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        try {
            Iterable<Teacher> teachers = teacherRegistrationService.getAllTeachers();
            Iterable<TeacherDTO> teacherDTOs = teacherAssembler.toDTOs(teachers);
            CollectionModel<EntityModel<TeacherDTO>> collectionModel = teacherHateoasAssembler.toCollectionModel(teacherDTOs);
            return ResponseEntity.ok(collectionModel);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @PostMapping
    public ResponseEntity<?> registerTeacher(
            @Valid @RequestBody RegisterTeacherRequestDTO requestDTO) {
        try {
            RegisterTeacherCommandDTO teacherCommandDTO = teacherAssembler.toRegisterTeacherCommandDTO(requestDTO);
            Teacher teacher = teacherRegistrationService.createAndSaveTeacher(teacherCommandDTO);
            TeacherDTO teacherDTO = teacherAssembler.toDTO(teacher);
            return ResponseEntity.status(HttpStatus.CREATED).body(teacherDTO);

        } catch (BusinessRuleViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable("id") String id) {
        try {
            TeacherID teacherID = teacherAssembler.fromStringToTeacherID(id);

            Optional<Teacher> teacher = teacherRegistrationService.getTeacherById(teacherID);
            if (teacher.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
            }

            TeacherDTO teacherDTO = teacherAssembler.toDTO(teacher.get());
            EntityModel<TeacherDTO> modelResponseDTO = teacherHateoasAssembler.toModel(teacherDTO);
            return ResponseEntity.ok(modelResponseDTO);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred");
        }
    }

    @PostMapping("/{teacherId}/careerprogressions/category")
    public ResponseEntity<Object> updateTeacherCategory(@PathVariable ("teacherId") String teacherId,
                                                   @Valid @RequestBody UpdateTeacherCategoryRequestDTO request) throws Exception {

        UpdateTeacherCategoryCommand command = careerAssembler.toUpdateTeacherCategoryCommand(teacherId,request);

        UpdateTeacherCategoryDTO result = careerService.updateTeacherCategory(command);

        UpdateTeacherCategoryResponseDTO responseDTO = careerAssembler.toUpdateTeacherCategoryResponseDTO(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(updateTeacherCategoryHateoasAssemblerInterface.toModel(responseDTO));
    }

    @PostMapping("/careerprogressions/{teacherID}/working-percentage")
    public ResponseEntity<?> updateTeacherWorkingPercentage(
            @PathVariable ("teacherID") String teacherID,
            @Valid @RequestBody UpdateTeacherWorkingPercentageRequestDTO request) {
        try {
            UpdateTeacherWorkingPercentageCommand command = careerAssembler.toUpdateTeacherWorkingPercentageCommand(teacherID, request);
            Optional<TeacherCareerProgression> result = careerService.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

            if (result.isEmpty()) {
                return ResponseEntity.badRequest().body("Unable to update teacher working percentage");
            }

            UpdateTeacherWorkingPercentageResponseDTO responseDTO = careerAssembler.toUpdateWorkingPercentageDTO(result.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    updateTeacherWorkingPercentageHateoasAssembler.toModel(responseDTO));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", e);
        }
    }

    @PostMapping("/with-relevant-data")
    public ResponseEntity<?> registerTeacherWithRelevantData(
            @Valid @RequestBody TeacherWithRelevantDataRequestDTO teacherWithRelevantDataRequestDTO) {
        try {

            TeacherID teacherID = teacherWithRelevantDataAssembler.toTeacherID(teacherWithRelevantDataRequestDTO.id());
            Name name = teacherWithRelevantDataAssembler.toName(teacherWithRelevantDataRequestDTO.name());
            Email email = teacherWithRelevantDataAssembler.toEmail(teacherWithRelevantDataRequestDTO.email());
            NIF nif = teacherWithRelevantDataAssembler.toNif(teacherWithRelevantDataRequestDTO.nif(), teacherWithRelevantDataRequestDTO.country());
            PhoneNumber phoneNumber = teacherWithRelevantDataAssembler.toPhoneNumber(teacherWithRelevantDataRequestDTO.countryCode(), teacherWithRelevantDataRequestDTO.phoneNumber());
            AcademicBackground academicBackground = teacherWithRelevantDataAssembler.toAcademicBackground(teacherWithRelevantDataRequestDTO.academicBackground());
            Street street = teacherWithRelevantDataAssembler.toStreet(teacherWithRelevantDataRequestDTO.street());
            PostalCode postalCode = teacherWithRelevantDataAssembler.toPostalCode(teacherWithRelevantDataRequestDTO.postalCode());
            Location location = teacherWithRelevantDataAssembler.toLocation(teacherWithRelevantDataRequestDTO.location());
            Country country = teacherWithRelevantDataAssembler.toCountry(teacherWithRelevantDataRequestDTO.country());
            DepartmentID departmentID = teacherWithRelevantDataAssembler.toDepartmentID(teacherWithRelevantDataRequestDTO.departmentID());
            Date date = teacherWithRelevantDataAssembler.toDate(teacherWithRelevantDataRequestDTO.date());
            TeacherCategoryID teacherCategoryID = teacherWithRelevantDataAssembler.toTeacherCategoryID(teacherWithRelevantDataRequestDTO.teacherCategoryID());
            WorkingPercentage workingPercentage = teacherWithRelevantDataAssembler.toWorkingPercentage(teacherWithRelevantDataRequestDTO.workingPercentage());


            TeacherWithRelevantDataDTO teacherWithRelevantDataDTO = teacherWithRelevantDataService.registerTeacherWithRelevantData(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID, date, teacherCategoryID, workingPercentage);
            return ResponseEntity.status(HttpStatus.CREATED).body(iTeacherWithRelevantDataHateoasAssembler.toModel(teacherWithRelevantDataDTO));

        } catch (BusinessRuleViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

}
