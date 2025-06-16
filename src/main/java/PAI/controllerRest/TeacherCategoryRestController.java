package PAI.controllerRest;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.assembler.teacherCategory.ITeacherCategoryExternalAssembler;
import PAI.assembler.teacherCategory.ITeacherCategoryHateoasAssembler;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import PAI.exception.AlreadyRegisteredException;
import PAI.exception.ErrorResponse;
import PAI.service.teacherCategory.ITeacherCategoryService;
import PAI.utils.ValidationUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher-categories")
public class TeacherCategoryRestController {

    public final ITeacherCategoryService teacherCategoryService;

    public final ITeacherCategoryExternalAssembler teacherCategoryAssembler;

    public final ITeacherCategoryHateoasAssembler teacherCategoryHateoasAssembler;

    public TeacherCategoryRestController(ITeacherCategoryService teacherCategoryService,
                                         ITeacherCategoryExternalAssembler teacherCategoryAssembler,
                                         ITeacherCategoryHateoasAssembler teacherCategoryHateoasAssembler) {

        this.teacherCategoryService = ValidationUtils.validateNotNull
                (teacherCategoryService, "Teacher Category Service Interface");
        this.teacherCategoryAssembler = ValidationUtils.validateNotNull
                (teacherCategoryAssembler, "Teacher Category Assembler Interface");
        this.teacherCategoryHateoasAssembler = ValidationUtils.validateNotNull
                (teacherCategoryHateoasAssembler, "Teacher Category Hateoas Assembler Interface");
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> configureTeacherCategory
            (@Valid @RequestBody TeacherCategoryRequestDTO teacherCategoryRequestDTO) throws Exception {

        try {
            Name nameVO = teacherCategoryAssembler.toNameVO(teacherCategoryRequestDTO);

            TeacherCategoryDTO teacherCategoryDTO =
                    teacherCategoryService.configureTeacherCategory(nameVO);

            TeacherCategoryResponseDTO teacherCategoryResponseDTO =
                    teacherCategoryAssembler.toResponseDTO(teacherCategoryDTO);
            return new ResponseEntity<>(teacherCategoryHateoasAssembler.toModel(teacherCategoryResponseDTO),HttpStatus.CREATED);

        } catch (AlreadyRegisteredException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.toString(),
                                            "Error Registering Teacher Category: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Object> getTeacherCategoryById (@PathVariable("id") String id) throws Exception {

        TeacherCategoryID teacherCategoryID = teacherCategoryAssembler.toTeacherCategoryIDVO(id);

        TeacherCategoryDTO teacherCategoryDTO = teacherCategoryService.getTeacherCategoryByID(teacherCategoryID);

        TeacherCategoryResponseDTO teacherCategoryResponseDTO = teacherCategoryAssembler.toResponseDTO(teacherCategoryDTO);

        return ResponseEntity.ok(teacherCategoryResponseDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllTeacherCategories() {
        try{
            Iterable<TeacherCategory> teacherCategories = teacherCategoryService.getAllTeacherCategories();
            Iterable<TeacherCategoryResponseDTO> teacherCategoryResponseDTOS = this.teacherCategoryAssembler.toDTOs(teacherCategories);
            return ResponseEntity.ok(teacherCategoryResponseDTOS);}
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

}
