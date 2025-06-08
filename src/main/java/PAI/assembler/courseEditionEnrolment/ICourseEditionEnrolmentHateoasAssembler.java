package PAI.assembler.courseEditionEnrolment;

import org.springframework.lang.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

public interface ICourseEditionEnrolmentHateoasAssembler extends RepresentationModelAssembler<CourseEditionEnrolmentDto, EntityModel<CourseEditionEnrolmentDto>> {

    @NonNull
    EntityModel<CourseEditionEnrolmentDto> toModel(@NonNull CourseEditionEnrolmentDto dto);

}
