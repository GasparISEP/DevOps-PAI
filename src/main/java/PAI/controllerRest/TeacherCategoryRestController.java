package PAI.controllerRest;

import PAI.VOs.Name;
import PAI.assembler.teacherCategory.ITeacherCategoryAssembler;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import PAI.service.teacherCategory.ITeacherCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachercategories")
public class TeacherCategoryRestController {

    public final ITeacherCategoryService teacherCategoryService;
    public final ITeacherCategoryAssembler teacherCategoryAssembler;

    public TeacherCategoryRestController(ITeacherCategoryService teacherCategoryService,
                                         ITeacherCategoryAssembler teacherCategoryAssembler) {

       validateNotNull(teacherCategoryService, "Teacher Category Service Interface");
       validateNotNull(teacherCategoryAssembler, "Teacher Category Assembler Interface");

        this.teacherCategoryService = teacherCategoryService;
        this.teacherCategoryAssembler = teacherCategoryAssembler;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> configureTeacherCategory(@Valid @RequestBody TeacherCategoryRequestDTO teacherCategoryRequestDTO) {
        try{
            Name nameVO = teacherCategoryAssembler.toVO(teacherCategoryRequestDTO);

            TeacherCategory teacherCategory = teacherCategoryService.configureTeacherCategory(nameVO);

            TeacherCategoryResponseDTO teacherCategoryResponseDTO = teacherCategoryAssembler.toDTO(teacherCategory);

            return new ResponseEntity<>(teacherCategoryResponseDTO,HttpStatus.CREATED);

        } catch (Exception e){

            return new ResponseEntity<>("Failed to configure teacher category. Please check your input!",HttpStatus.BAD_REQUEST);
        }
    }

    //Verify if the constructor parameters are valid

    private <T> void validateNotNull(T dependency, String name) {
        if (dependency == null) {
            throw new IllegalArgumentException(name + " cannot be null!");
        }
    }
}
