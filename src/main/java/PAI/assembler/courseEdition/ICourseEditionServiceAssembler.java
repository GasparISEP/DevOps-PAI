package PAI.assembler.courseEdition;

import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionResponseDTO;

public interface ICourseEditionServiceAssembler {
    CourseEditionResponseDTO toResponseDTO(CourseEdition courseEdition);
}
