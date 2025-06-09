package PAI.assembler.studentGrade;

import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentGradeRepresentationModelAssembler
        implements RepresentationModelAssembler<GradeAStudentResponseDTO, EntityModel<GradeAStudentResponseDTO>> {

    @Override
    public EntityModel<GradeAStudentResponseDTO> toModel(GradeAStudentResponseDTO dto) {
        return EntityModel.of(dto,

                // Link para obter os dados da média da CourseEdition (já sem throws)
                linkTo(methodOn(CourseEditionRestController.class)
                        .getCourseEditionAverageGrade(
                                dto._programmeId(),
                                dto._schoolYearId(),
                                dto._courseId(),
                                dto._studyPlanId()
                        )
                ).withRel("averageGrade"),

                // Link para obter a taxa de aprovação da CourseEdition (já sem throws)
                linkTo(methodOn(CourseEditionRestController.class)
                        .getCourseEditionApprovalRate(
                                dto._programmeId(),
                                dto._schoolYearId(),
                                dto._courseId(),
                                dto._studyPlanId()
                        )
                ).withRel("approvalRate"),

                // Link self para o POST (isto é simbólico, já que o POST não aceita GETs)
                linkTo(CourseEditionRestController.class).slash("studentgrades/register")
                        .withSelfRel()
        );
    }
}
