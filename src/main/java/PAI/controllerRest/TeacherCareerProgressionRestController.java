package PAI.controllerRest;

import PAI.VOs.TeacherCareerProgressionID;
import PAI.assembler.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryResponseDTO;
import PAI.service.teacherCareerProgression.ICreateTeacherCareerProgressionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static PAI.utils.ValidationUtils.validateNotNull;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teacher-career-progressions")
public class TeacherCareerProgressionRestController {

    private final ICreateTeacherCareerProgressionService careerService;
    private final ITeacherCareerProgressionAssembler careerAssembler;

    public TeacherCareerProgressionRestController
            (ICreateTeacherCareerProgressionService careerService, ITeacherCareerProgressionAssembler careerAssembler) {
        this.careerService = validateNotNull(careerService,  "Teacher Career Progression Service Interface");
        this.careerAssembler = validateNotNull(careerAssembler,  "Teacher Career Progression Assembler Interface");
    }

    @GetMapping
    public ResponseEntity<Object> getAllTeacherCareerProgression () throws Exception {
        List<UpdateTeacherCategoryDTO> listTCP = careerService.getAllTeacherCareerProgression();

        List<UpdateTeacherCategoryResponseDTO> listTCPResponseDTO = careerAssembler.toResponseDTOs(listTCP);

        return ResponseEntity.status(HttpStatus.OK).body(listTCPResponseDTO);
    }

    @GetMapping ("{id}")
    public ResponseEntity <Object> getTeacherCareerProgressionByID (@PathVariable("id") String id) {
        TeacherCareerProgressionID teacherCareerProgressionID = new TeacherCareerProgressionID(UUID.fromString(id));

        UpdateTeacherCategoryDTO updateTeacherCategoryDTO = careerService.getTeacherCareerProgressionByID(teacherCareerProgressionID);

        UpdateTeacherCategoryResponseDTO responseDTO = careerAssembler.toUpdateTeacherCategoryResponseDTO(updateTeacherCategoryDTO);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
