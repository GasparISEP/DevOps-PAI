package PAI.controllerRest;


import PAI.assembler.teacher.ITeacherAssembler;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacher.TeacherDTO;
import PAI.dto.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryRequestDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryResponseDTO;
import PAI.service.teacher.ITeacherService;
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

    private final ITeacherService teacherService;
    private final ITeacherAssembler teacherAssembler;
    private final ITeacherCareerProgressionServiceV2 careerService;
    private final ITeacherCareerProgressionAssembler careerAssembler;

    public TeacherRestController(ITeacherService teacherService, ITeacherAssembler teacherAssembler, ITeacherCareerProgressionServiceV2 careerService, ITeacherCareerProgressionAssembler careerAssembler) {
        this.teacherService = teacherService;
        this.teacherAssembler = teacherAssembler;
        this.careerService = careerService;
        this.careerAssembler = careerAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        try {
            Iterable<Teacher> teachers = teacherService.getAllTeachers();
            Iterable<TeacherDTO> teacherDTOs = teacherAssembler.toDTOs(teachers);
            return ResponseEntity.ok(teacherDTOs);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @PostMapping
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
