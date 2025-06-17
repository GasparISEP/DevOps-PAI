package PAI.assembler.ProgrammeAndCourses;

import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import org.springframework.hateoas.EntityModel;

public interface IProgrammeAndCoursesHateoasAssembler {
    EntityModel<StudentEnrolmentResultDto> toModel(StudentEnrolmentResultDto dto);
}
