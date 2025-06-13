package PAI.assembler.course;

import PAI.VOs.Acronym;
import PAI.VOs.AvailableCourseInfo;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.domain.course.Course;
import PAI.domain.teacher.Teacher;
import PAI.dto.ProgrammeAndCourses.AvailableCoursesInfoRspDTO;
import PAI.dto.course.CourseDTOCommand;
import PAI.dto.course.CourseIDDTO;
import PAI.dto.course.CourseRequestDTO;
import PAI.dto.course.CourseResponseDTO;
import PAI.dto.teacher.TeacherDTO;

import java.util.List;

public interface ICourseAssembler {

    CourseDTOCommand toDomain (CourseRequestDTO requestDTO);

    CourseResponseDTO toDTO (Course course);

    CourseIDDTO toIDDTO(CourseID courseID);

    List<CourseIDDTO> toDTOList(List<CourseID> courses);

    Iterable<CourseResponseDTO> toDTOs(Iterable<Course> courses);

    AvailableCoursesInfoRspDTO toAvailableCourseDTO (AvailableCourseInfo availableCourseInfo);

    List<AvailableCoursesInfoRspDTO> toAvailableCourseDTOs (List<AvailableCourseInfo> list);
}
