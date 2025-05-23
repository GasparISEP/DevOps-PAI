package PAI.controllerRest;


import PAI.assembler.teacher.ITeacherAssembler;
import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.TeacherDTO;
import PAI.service.teacher.ITeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherRestController {

    private final ITeacherService teacherService;
    private final ITeacherAssembler teacherAssembler;

    public TeacherRestController(ITeacherService teacherService, ITeacherAssembler teacherAssembler) {
        this.teacherService = teacherService;
        this.teacherAssembler = teacherAssembler;
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
}
