package PAI.controllerRest;


import PAI.assembler.teacher.ITeacherAssembler;
import PAI.service.teacher.ITeacherService;
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
}
