package PAI.assembler.courseEdition;

import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CourseEditionServiceResponseDTO;

public interface ICourseEditionServiceAssembler {

    CourseEditionServiceResponseDTO toServiceResponseDTO(CourseEdition courseEdition);

}
