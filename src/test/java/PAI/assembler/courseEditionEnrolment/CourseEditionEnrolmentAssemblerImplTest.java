package PAI.assembler.courseEditionEnrolment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

class CourseEditionEnrolmentAssemblerImplTest {

    private ICourseEditionEnrolmentFactory factory;
    private CourseEditionEnrolmentAssemblerImpl assembler;
    private CourseEditionEnrolmentDto dto;

    @BeforeEach
    void setUp() {
        factory = mock(ICourseEditionEnrolmentFactory.class);
        assembler = new CourseEditionEnrolmentAssemblerImpl(factory);
        dto = mock(CourseEditionEnrolmentDto.class);
    }

    @Test
    void shouldCreateCourseEditionEnrolmentWithValidDto() throws Exception {
        // Arrange
        when(dto.studentUniqueNumber()).thenReturn(1100000);
        when(dto.programmeName()).thenReturn("LEI");
        when(dto.programmeAcronym()).thenReturn("LEIC");
        when(dto.schoolYearId()).thenReturn("123e4567-e89b-12d3-a456-426614174000");
        when(dto.courseAcronym()).thenReturn("ESOFT");
        when(dto.courseName()).thenReturn("Engineering Software");
        when(dto.studyPlanDate()).thenReturn("01-01-2024");

        CourseEditionEnrolment expectedEnrolment = mock(CourseEditionEnrolment.class);
        when(factory.createCourseEditionEnrolment(any(StudentID.class), any(CourseEditionID.class)))
            .thenReturn(expectedEnrolment);

        // Act
        CourseEditionEnrolment result = assembler.toDomain(dto);

        // Assert
        assertEquals(expectedEnrolment, result);
    }
}
