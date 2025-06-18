package PAI.assembler.studentGrade;

import PAI.controllerRest.StudentRestController;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class StudentGradeEnrolmentAssemblerHateoas implements RepresentationModelAssembler<GradeAStudentResponseDTO, EntityModel<GradeAStudentResponseDTO>> {

    @Override
    public EntityModel<GradeAStudentResponseDTO> toModel(GradeAStudentResponseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(StudentRestController.class)
                        .getStudentByID(dto._studentUniqueNumber()))
                        .withRel("student-details")
        );
    }
}
