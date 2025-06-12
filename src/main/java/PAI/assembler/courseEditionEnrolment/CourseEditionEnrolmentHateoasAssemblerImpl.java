package PAI.assembler.courseEditionEnrolment;

import org.springframework.lang.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

@Component
public class CourseEditionEnrolmentHateoasAssemblerImpl implements RepresentationModelAssembler<CourseEditionEnrolmentDto, EntityModel<CourseEditionEnrolmentDto>>, ICourseEditionEnrolmentHateoasAssembler{

    @Override
    @NonNull
    public EntityModel<CourseEditionEnrolmentDto> toModel(@NonNull CourseEditionEnrolmentDto dto){
        return EntityModel.of(dto,
                linkTo(methodOn(CourseEditionRestController.class)
                        .removeStudentEnrolmentFromACourseEdition(dto))
                        .withRel("delete")
        );
    }
}
