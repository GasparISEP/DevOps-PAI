package PAI.assembler.course;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.ProgrammeAndCourses.AvailableCoursesInfoRspDTO;
import PAI.dto.course.CourseDTOCommand;
import PAI.dto.course.CourseIDDTO;
import PAI.dto.course.CourseRequestDTO;
import PAI.dto.course.CourseResponseDTO;
import PAI.dto.teacher.TeacherDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CourseAssemblerImpl implements ICourseAssembler {

    public CourseDTOCommand toDomain (CourseRequestDTO requestDTO) {

        Acronym acronym = new Acronym(requestDTO._acronym());
        Name name = new Name(requestDTO._name());
        CourseID courseID = new CourseID(acronym, name);

        return new CourseDTOCommand(courseID, name, acronym);
    }

    public CourseResponseDTO toDTO (Course course) {

        String acronym = course.getAcronym().getAcronym();
        String name = course.getName().getName();

        return new CourseResponseDTO(acronym, name);
    }

    public CourseIDDTO toIDDTO(CourseID courseID) {
        if (courseID == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        String courseName = courseID.getCourseNameValue();
        String courseAcronym = courseID.getCourseAcronymValue();
        return new CourseIDDTO(courseAcronym, courseName);
    }

    @Override
    public List<CourseIDDTO> toDTOList(List<CourseID> courses) {
        List<CourseIDDTO> dtos = new ArrayList<>();
        for (CourseID existingCourse : courses){
            dtos.add(toIDDTO(existingCourse));
        }
        return dtos;
    }

    @Override
    public Iterable<CourseResponseDTO> toDTOs(Iterable<Course> courses) {
        if (courses == null) {
            return Collections.emptyList();
        }

        List<CourseResponseDTO> listDTO = new ArrayList<>();
        for (Course course : courses) {
            CourseResponseDTO courseResponseDTO = toDTO(course);
            listDTO.add(courseResponseDTO);
        }
        return listDTO;
    }

    public AvailableCoursesInfoRspDTO toAvailableCourseDTO (AvailableCourseInfo availableCourseInfo){
        if (availableCourseInfo == null) throw new IllegalArgumentException("Available course cannot be null");
        String acronym = availableCourseInfo.courseID().getAcronym().toString();
        String name = availableCourseInfo.courseID().getName().getName();
        double qtyECTs = availableCourseInfo.qtyEcts().getQuantity();
        int curricularYear = availableCourseInfo.curricularYear().toInt();
        int semester = availableCourseInfo.semester().toInt();
        return new AvailableCoursesInfoRspDTO(acronym,name,qtyECTs,curricularYear,semester);
    }

    public List<AvailableCoursesInfoRspDTO> toAvailableCourseDTOs (List<AvailableCourseInfo> list){
        List<AvailableCoursesInfoRspDTO> result = new ArrayList<>();
        for (AvailableCourseInfo existingOne : list){
            AvailableCoursesInfoRspDTO availableCoursesInfoRspDTO = toAvailableCourseDTO(existingOne);
            result.add(availableCoursesInfoRspDTO);
        }
        return result;
    }
}
