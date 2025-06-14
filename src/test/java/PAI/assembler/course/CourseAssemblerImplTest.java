package PAI.assembler.course;

import PAI.VOs.*;
import PAI.assembler.teacher.TeacherAssemblerImpl;
import PAI.domain.course.Course;
import PAI.domain.teacher.Teacher;
import PAI.dto.ProgrammeAndCourses.AvailableCoursesInfoRspDTO;
import PAI.dto.course.CourseDTOCommand;
import PAI.dto.course.CourseIDDTO;
import PAI.dto.course.CourseRequestDTO;
import PAI.dto.course.CourseResponseDTO;
import PAI.dto.teacher.TeacherDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseAssemblerImplTest {

    @Test
    void toIDDTOShouldReturnCourseIDDTO() {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getCourseAcronymValue()).thenReturn("DSOFT");
        when(courseIDDouble.getCourseNameValue()).thenReturn("Desenvolvimento de Software");

        CourseAssemblerImpl assembler = new CourseAssemblerImpl();

        // Act
        CourseIDDTO result = assembler.toIDDTO(courseIDDouble);

        // Assert
        assertInstanceOf(CourseIDDTO.class, result);
        assertEquals("DSOFT", result.acronym());
        assertEquals("Desenvolvimento de Software", result.name());
    }

    @Test
    void toIDDTOShouldThrowExceptionWhenCourseIDIsNull() {
        // Arrange
        CourseAssemblerImpl assembler = new CourseAssemblerImpl();

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> assembler.toIDDTO(null));
        assertEquals("Course ID cannot be null", exception.getMessage());
    }

    @Test
    void toDomainShouldReturnCourseDTOCommand () {
        // Arrange
        CourseRequestDTO requestDTODouble = mock(CourseRequestDTO.class);

        when(requestDTODouble._acronym()).thenReturn("DSOFT");
        when(requestDTODouble._name()).thenReturn("Desenvolvimento de Software");

        CourseAssemblerImpl assembler = new CourseAssemblerImpl();

        // Act
        CourseDTOCommand result = assembler.toDomain(requestDTODouble);

        // Assert
        assertInstanceOf(CourseDTOCommand.class, result);
    }

    @Test
    void toDTOShouldReturnCourseResponseDTO () {
        // Arrange
        Course courseDouble = mock(Course.class);

        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);

        when(courseDouble.getAcronym()).thenReturn(acronym);
        when(courseDouble.getName()).thenReturn(name);

        CourseAssemblerImpl assembler = new CourseAssemblerImpl();

        // Act
        CourseResponseDTO result = assembler.toDTO(courseDouble);

        // Assert
        assertInstanceOf(CourseResponseDTO.class, result);
    }

    @Test
    void shouldReturnListOfDTOs() {
        // Arrange
        CourseAssemblerImpl assembler = new CourseAssemblerImpl();
        CourseID courseID = mock(CourseID.class);

        List<CourseID> domainList = List.of(courseID);

        // Act
        List<CourseIDDTO> dtoList = assembler.toDTOList(domainList);

        // Assert
        assertEquals(1, dtoList.size());
    }

    @Test
    void shouldConvertListOfCoursesToDTOs() {
        // Arrange
        Course course = mock(Course.class);

        CourseGeneratedID courseGeneratedID = mock(CourseGeneratedID.class);
        when(course.getCourseGeneratedID()).thenReturn(courseGeneratedID);

        CourseID courseID = mock(CourseID.class);
        when(course.identity()).thenReturn(courseID);

        Name name = mock(Name.class);
        when(course.getName()).thenReturn(name);
        when(name.getName()).thenReturn("Desenvolvimento de Software");

        Acronym acronym = mock(Acronym.class);
        when(course.getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("DSOFT");

        Iterable<Course> courses = List.of(course);
        CourseAssemblerImpl courseAssembler = new CourseAssemblerImpl();

        // Act
        Iterable<CourseResponseDTO> courseResponseDTOS = courseAssembler.toDTOs(courses);

        // Assert
        assertNotNull(courseResponseDTOS);
        assertTrue(courseResponseDTOS.iterator().hasNext());
    }

    @Test
    void shouldReturnEmptyListWhenInputIsNull() {
        // Arrange
        CourseAssemblerImpl courseAssembler = new CourseAssemblerImpl();
        // Act
        Iterable<CourseResponseDTO> courseDTOs = courseAssembler.toDTOs(null);

        // Assert
        assertNotNull(courseDTOs);
        assertFalse(courseDTOs.iterator().hasNext());
    }

    @Test
    void shouldReturnEmptyListWhenInputIsEmpty() {
        // Arrange
        CourseAssemblerImpl courseAssembler = new CourseAssemblerImpl();
        // Act
        Iterable<CourseResponseDTO> courseDTOs = courseAssembler.toDTOs(List.of());
        // Assert
        assertNotNull(courseDTOs);
        assertFalse(courseDTOs.iterator().hasNext());
    }

    @Test
    void toAvailableCourseDTO_ShouldReturnDTO() {
        // Arrange
        CourseAssemblerImpl courseAssembler = new CourseAssemblerImpl();

        Acronym acronym = mock(Acronym.class);
        when(acronym.toString()).thenReturn("DSO");

        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Desenvolvimento de Software");

        CourseID courseID = mock(CourseID.class);
        when(courseID.getAcronym()).thenReturn(acronym);
        when(courseID.getName()).thenReturn(name);

        CourseQuantityCreditsEcts qty = mock(CourseQuantityCreditsEcts.class);
        when(qty.getQuantity()).thenReturn(6.0);

        CurricularYear curricularYear = mock(CurricularYear.class);
        when(curricularYear.toInt()).thenReturn(1);

        Semester semester = mock(Semester.class);
        when(semester.toInt()).thenReturn(1);

        AvailableCourseInfo availableCourseInfo = mock(AvailableCourseInfo.class);
        when(availableCourseInfo.courseID()).thenReturn(courseID);
        when(availableCourseInfo.qtyEcts()).thenReturn(qty);
        when(availableCourseInfo.curricularYear()).thenReturn(curricularYear);
        when(availableCourseInfo.semester()).thenReturn(semester);
        // Act
        AvailableCoursesInfoRspDTO dto = courseAssembler.toAvailableCourseDTO(availableCourseInfo);

        // Assert
        assertNotNull(dto);
        assertEquals("DSO", dto.acronym());
        assertEquals("Desenvolvimento de Software", dto.name());
        assertEquals(6.0, dto.qtyECTs());
        assertEquals(1,dto.curricularYear());
        assertEquals(1,dto.semester());
    }
    @Test
    void toAvailableCourseDTO_ShouldThrowWhenNull() {
        // Arrange
        CourseAssemblerImpl courseAssembler = new CourseAssemblerImpl();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> courseAssembler.toAvailableCourseDTO(null));
        assertEquals("Available course cannot be null", exception.getMessage());
    }

    @Test
    void toAvailableCourseDTOs_ShouldReturnListOfDTOs() {
        // Arrange
        CourseAssemblerImpl courseAssembler = new CourseAssemblerImpl();

        AvailableCourseInfo course1 = mock(AvailableCourseInfo.class);
        CourseID courseID = mock(CourseID.class);
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseQuantityCreditsEcts ects = mock(CourseQuantityCreditsEcts.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        Semester semester = mock(Semester.class);
        when(course1.courseID()).thenReturn(courseID);
        when(courseID.getAcronym()).thenReturn(acronym);
        when(courseID.getName()).thenReturn(name);
        when(acronym.toString()).thenReturn("DSO");
        when(name.getName()).thenReturn("Desenvolvimento de Software");
        when(course1.qtyEcts()).thenReturn(ects);
        when(ects.getQuantity()).thenReturn(6.0);
        when(course1.curricularYear()).thenReturn(curricularYear);
        when(curricularYear.toInt()).thenReturn(1);
        when(course1.semester()).thenReturn(semester);
        when(semester.toInt()).thenReturn(1);

        List<AvailableCourseInfo> domainList = List.of(course1);

        // Act
        List<AvailableCoursesInfoRspDTO> dtoList = courseAssembler.toAvailableCourseDTOs(domainList);

        // Assert
        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());

    }

    @Test
    void toAvailableCourseDTOs_ShouldReturnEmptyListWhenInputIsEmpty() {
        // Arrange
        CourseAssemblerImpl courseAssembler = new CourseAssemblerImpl();

        // Act
        List<AvailableCoursesInfoRspDTO> result = courseAssembler.toAvailableCourseDTOs(List.of());

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
