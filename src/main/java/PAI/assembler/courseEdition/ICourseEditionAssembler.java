package PAI.assembler.courseEdition;
import java.util.List;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.SelectedCourseEditionIdDTO;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;


public interface ICourseEditionAssembler {
    CreateCourseEditionCommand toCommand(CourseEditionRequestDTO dto);
    CourseEditionResponseDTO toResponseDTO(CourseEdition domain);
    TeacherID createTeacherID (String teacherID);
    CourseEditionID fromDtoToCourseEditionID (SelectedCourseEditionIdDTO courseEditionDTO)throws Exception;
    List<CourseEditionResponseDTO> toResponseDTOList(List<CourseEditionID> courseEditionIDs);
}
