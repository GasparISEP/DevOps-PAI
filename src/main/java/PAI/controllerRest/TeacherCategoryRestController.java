package PAI.controllerRest;

import PAI.VOs.Name;
import PAI.assembler.teacherCategory.ITeacherCategoryExternalAssembler;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import PAI.service.teacherCategory.ITeacherCategoryService;
import PAI.utils.ValidationUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachercategories")
public class TeacherCategoryRestController {

    public final ITeacherCategoryService teacherCategoryService;

    public final ITeacherCategoryExternalAssembler teacherCategoryAssembler;

    public TeacherCategoryRestController(ITeacherCategoryService teacherCategoryService,
                                         ITeacherCategoryExternalAssembler teacherCategoryAssembler) {

        this.teacherCategoryService = ValidationUtils.validateNotNull(teacherCategoryService, "Teacher Category Service Interface");;
        this.teacherCategoryAssembler = ValidationUtils.validateNotNull(teacherCategoryAssembler, "Teacher Category Assembler Interface");;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> configureTeacherCategory
            (@Valid @RequestBody TeacherCategoryRequestDTO teacherCategoryRequestDTO) throws Exception {

            Name nameVO = teacherCategoryAssembler.toVO(teacherCategoryRequestDTO);

            TeacherCategoryDTO teacherCategoryDTO =
                    teacherCategoryService.configureTeacherCategory(nameVO);

            TeacherCategoryResponseDTO teacherCategoryResponseDTO =
                    teacherCategoryAssembler.toResponseDTO(teacherCategoryDTO);

            return new ResponseEntity<>(teacherCategoryResponseDTO,HttpStatus.CREATED);
    }
}
