package PAI.assembler.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.domain.course.Course;
import PAI.dto.course.CourseDTOCommand;
import PAI.dto.course.CourseIDDTO;
import PAI.dto.course.CourseRequestDTO;
import PAI.dto.course.CourseResponseDTO;

import java.util.List;

public interface ICourseAssembler {

    CourseDTOCommand toDomain (CourseRequestDTO requestDTO);

    CourseResponseDTO toDTO (Course course);

    CourseIDDTO toIDDTO(CourseID courseID);

    List<CourseIDDTO> toDTOList(List<CourseID> courses);
}
