package PAI.assembler.courseEdition;
import java.util.List;

import PAI.VOs.*;
import PAI.VOs.CourseEditionID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.*;


public interface ICourseEditionAssembler {
    CreateCourseEditionCommand toCommand(CourseEditionRequestDTO dto);
    CourseEditionResponseDTO toResponseDTO(CourseEdition domain);
    TeacherID createTeacherID (String teacherID);
    CourseEditionID fromDtoToCourseEditionID (SelectedCourseEditionIdDTO courseEditionDTO)throws Exception;
    CourseEditionGeneratedID fromDtoToCourseEditionGeneratedID (SelectedCourseEditionGeneratedIdDTO courseEditionDTO)throws Exception;
    List<CourseEditionResponseDTO> toResponseDTOList(List<CourseEditionID> courseEditionIDs);
    ProgrammeEditionID toProgrammeEditionID(CourseEditionRequestDTO courseEditionRequestDTO) throws Exception;
    CourseInStudyPlanID toCourseInStudyPlanID(CourseEditionRequestDTO courseEditionRequestDTO) throws Exception;
}
