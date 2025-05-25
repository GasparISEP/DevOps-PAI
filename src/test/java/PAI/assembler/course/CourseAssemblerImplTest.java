package PAI.assembler.course;

import PAI.VOs.Acronym;
import PAI.VOs.Name;
import PAI.domain.course.Course;
import PAI.dto.course.CourseDTOCommand;
import PAI.dto.course.CourseRequestDTO;
import PAI.dto.course.CourseResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseAssemblerImplTest {

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
}