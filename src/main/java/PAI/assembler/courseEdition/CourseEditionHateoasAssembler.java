package PAI.assembler.courseEdition;
import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.courseEdition.DefineRucResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CourseEditionHateoasAssembler implements RepresentationModelAssembler<DefineRucResponseDTO, EntityModel<DefineRucResponseDTO>>, ICourseEditionHateoasAssembler {

    @Override
    public EntityModel<DefineRucResponseDTO> toModel(DefineRucResponseDTO dto) {
        try {
            return EntityModel.of(dto,
                    linkTo(methodOn(CourseEditionRestController.class)
                            .defineRucForCourseEdition(null))
                            .withRel("define-ruc")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
