package PAI.assembler.courseEditionEnrolment;

import org.springframework.lang.NonNull;
import org.springframework.hateoas.EntityModel;

import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

public interface ICourseEditionEnrolmentHateoasAssembler {

    @NonNull
    EntityModel<CourseEditionEnrolmentDto> toModel(@NonNull CourseEditionEnrolmentDto dto);

}
