package PAI.assembler.studentGrade;

import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentGradeRepresentationModelAssembler
        implements RepresentationModelAssembler<GradeAStudentResponseDTO, EntityModel<GradeAStudentResponseDTO>> {

    @Override
    public EntityModel<GradeAStudentResponseDTO> toModel(GradeAStudentResponseDTO dto) {
        return EntityModel.of(dto, buildLinks(dto));
    }

    private List<Link> buildLinks(GradeAStudentResponseDTO dto) {
        List<Link> links = new ArrayList<>();

        try {
            // Link para a média da CourseEdition
            links.add(linkTo(methodOn(CourseEditionRestController.class)
                    .getCourseEditionAverageGrade(
                            dto._programmeId(),
                            dto._schoolYearId(),
                            dto._courseId(),
                            dto._studyPlanId()
                    )).withRel("averageGrade"));

            // Link para a taxa de aprovação da CourseEdition
            links.add(linkTo(methodOn(CourseEditionRestController.class)
                    .getCourseEditionApprovalRate(
                            dto._programmeId(),
                            dto._schoolYearId(),
                            dto._courseId(),
                            dto._studyPlanId()
                    )).withRel("approvalRate"));

            // Link simbólico do POST
            links.add(linkTo(CourseEditionRestController.class)
                    .slash("studentgrades/register")
                    .withSelfRel());

        } catch (Exception e) {
            // Podes fazer logging aqui se quiseres:
            // log.warn("Erro ao construir links HATEOAS para GradeAStudentResponseDTO", e);
        }

        return links;
    }
}
