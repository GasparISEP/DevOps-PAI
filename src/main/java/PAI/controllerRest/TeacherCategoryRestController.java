package PAI.controllerRest;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.assembler.teacherCategory.ITeacherCategoryExternalAssembler;
import PAI.assembler.teacherCategory.ITeacherCategoryHateoasAssembler;
import PAI.domain.teacherCategory.TeacherCategory;
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

    public final ITeacherCategoryHateoasAssembler teacherCategoryHateoasAssembler;

    public TeacherCategoryRestController(ITeacherCategoryService teacherCategoryService,
                                         ITeacherCategoryExternalAssembler teacherCategoryAssembler,
                                         ITeacherCategoryHateoasAssembler teacherCategoryHateoasAssembler) {

        this.teacherCategoryService = ValidationUtils.validateNotNull(teacherCategoryService, "Teacher Category Service Interface");
        this.teacherCategoryAssembler = ValidationUtils.validateNotNull(teacherCategoryAssembler, "Teacher Category Assembler Interface");
        this.teacherCategoryHateoasAssembler = ValidationUtils.validateNotNull(teacherCategoryHateoasAssembler, "Teacher Category Hateoas Assembler Interface");
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> configureTeacherCategory
            (@Valid @RequestBody TeacherCategoryRequestDTO teacherCategoryRequestDTO) throws Exception {

            Name nameVO = teacherCategoryAssembler.toNameVO(teacherCategoryRequestDTO);

            TeacherCategoryDTO teacherCategoryDTO =
                    teacherCategoryService.configureTeacherCategory(nameVO);

            TeacherCategoryResponseDTO teacherCategoryResponseDTO =
                    teacherCategoryAssembler.toResponseDTO(teacherCategoryDTO);

            return new ResponseEntity<>(teacherCategoryHateoasAssembler.toModel(teacherCategoryResponseDTO),HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Object> getTeacherCategoryById (@PathVariable("id") String id) throws Exception {

        TeacherCategoryID teacherCategoryID = teacherCategoryAssembler.toTeacherCategoryIDVO(id);

        TeacherCategoryDTO teacherCategoryDTO = teacherCategoryService.getTeacherCategoryByID(teacherCategoryID);

        TeacherCategoryResponseDTO teacherCategoryResponseDTO = teacherCategoryAssembler.toResponseDTO(teacherCategoryDTO);

        return ResponseEntity.ok(teacherCategoryResponseDTO);
    }
}
