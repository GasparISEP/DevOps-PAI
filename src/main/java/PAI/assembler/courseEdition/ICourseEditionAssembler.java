package PAI.assembler.courseEdition;
import java.util.List;

import PAI.VOs.*;
import PAI.VOs.CourseEditionID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.*;


public interface ICourseEditionAssembler {
    CreateCourseEditionCommand toCommand(CourseEditionRequestDTO dto);
    CourseEditionResponseDTO toResponseDTO(CourseEditionServiceResponseDTO courseEditionServiceResponseDTO);
    TeacherID createTeacherID (String teacherID);
    CourseEditionID fromDtoToCourseEditionID (SelectedCourseEditionIdDTO courseEditionDTO)throws Exception;
    CourseEditionGeneratedID fromDtoToCourseEditionGeneratedID (SelectedCourseEditionGeneratedIdDTO courseEditionDTO)throws Exception;
   // List<CourseEditionResponseDTO> toResponseDTOList(List<CourseEditionServiceResponseDTO> courseEditionServiceResponseDTO);
    ProgrammeEditionID toProgrammeEditionID(CourseEditionRequestDTO courseEditionRequestDTO) throws Exception;
    CourseInStudyPlanID toCourseInStudyPlanID(CourseEditionRequestDTO courseEditionRequestDTO) throws Exception;
    List<CourseEditionResponseIDDTO> toResponseIDDTOList(List<CourseEditionID> courseEditionIDs);
}
