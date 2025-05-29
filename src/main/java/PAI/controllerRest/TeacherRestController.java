package PAI.controllerRest;


import PAI.VOs.*;
import PAI.assembler.teacher.ITeacherAssembler;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacher.*;
import PAI.dto.teacherCareerProgression.*;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.teacher.ITeacherRegistrationService;
import PAI.service.teacher.ITeacherWithRelevantDataService;
import PAI.service.teacherCareerProgression.ITeacherCareerProgressionServiceV2;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherRestController {

    private final ITeacherRegistrationService teacherRegistrationService;
    private final ITeacherAssembler teacherAssembler;
    private final ITeacherCareerProgressionServiceV2 careerService;
    private final ITeacherCareerProgressionAssembler careerAssembler;
    private final ITeacherWithRelevantDataService teacherWithRelevantDataService;
    private final TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler;

    public TeacherRestController(ITeacherRegistrationService teacherService, ITeacherAssembler teacherAssembler, ITeacherCareerProgressionServiceV2 careerService, ITeacherCareerProgressionAssembler careerAssembler, ITeacherWithRelevantDataService teacherWithRelevantDataService, TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler) {
        this.teacherRegistrationService = teacherService;
        this.teacherAssembler = teacherAssembler;
        this.careerService = careerService;
        this.careerAssembler = careerAssembler;
        this.teacherWithRelevantDataService = teacherWithRelevantDataService;
        this.teacherWithRelevantDataAssembler = teacherWithRelevantDataAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        try {
            Iterable<Teacher> teachers = teacherRegistrationService.getAllTeachers();
            Iterable<TeacherDTO> teacherDTOs = teacherAssembler.toDTOs(teachers);
            return ResponseEntity.ok(teacherDTOs);

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

    @PostMapping("/careerprogressions/{teacherID}/categories")
    public ResponseEntity<?> updateTeacherCategory(@Valid @RequestBody UpdateTeacherCategoryRequestDTO request) {
        try {
            UpdateTeacherCategoryCommand command = careerAssembler.toUpdateTeacherCategoryCommand(request);
            Optional<TeacherCareerProgression> result = careerService.updateTeacherCategoryInTeacherCareerProgression(command);

            if (result.isEmpty()) {
                return ResponseEntity.badRequest().body("Unable to update teacher category");
            }

            UpdateTeacherCategoryResponseDTO responseDTO = careerAssembler.toUpdateCategoryDTO(result.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", e);
        }
    }

    @PostMapping("/careerprogressions/{teacherID}/working-percentage")
    public ResponseEntity<?> updateTeacherWorkingPercentage(
            @Valid @RequestBody UpdateTeacherWorkingPercentageRequestDTO request) {
        try {
            UpdateTeacherWorkingPercentageCommand command = careerAssembler.toUpdateTeacherWorkingPercentageCommand(request);
            Optional<TeacherCareerProgression> result = careerService.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

            if (result.isEmpty()) {
                return ResponseEntity.badRequest().body("Unable to update teacher working percentage");
            }

            UpdateTeacherWorkingPercentageResponseDTO responseDTO = careerAssembler.toUpdateWorkingPercentageDTO(result.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

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
            return ResponseEntity.status(HttpStatus.CREATED).body(teacherWithRelevantDataDTO);

        } catch (BusinessRuleViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }
}
