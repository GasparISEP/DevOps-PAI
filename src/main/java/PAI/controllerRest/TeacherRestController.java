package PAI.controllerRest;


import PAI.assembler.teacher.ITeacherAssembler;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacher.RegisterTeacherCommandDTO;
import PAI.dto.teacher.RegisterTeacherRequestDTO;
import PAI.dto.teacher.TeacherDTO;
import PAI.dto.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryRequestDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryResponseDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.teacher.ITeacherRegistrationService;
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

    public TeacherRestController(ITeacherRegistrationService teacherService, ITeacherAssembler teacherAssembler, ITeacherCareerProgressionServiceV2 careerService, ITeacherCareerProgressionAssembler careerAssembler) {
        this.teacherRegistrationService = teacherService;
        this.teacherAssembler = teacherAssembler;
        this.careerService = careerService;
        this.careerAssembler = careerAssembler;
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
}
