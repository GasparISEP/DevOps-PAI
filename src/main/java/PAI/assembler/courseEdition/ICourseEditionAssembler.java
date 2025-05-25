package PAI.assembler.courseEdition;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;


public interface ICourseEditionAssembler {
    CreateCourseEditionCommand toCommand(CourseEditionRequestDTO dto);
    CourseEditionResponseDTO toResponseDTO(CourseEdition domain);
}
