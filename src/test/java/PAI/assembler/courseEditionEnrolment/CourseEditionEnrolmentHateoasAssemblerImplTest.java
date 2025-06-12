package PAI.assembler.courseEditionEnrolment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionEnrolmentHateoasAssemblerImplTest {

    private CourseEditionEnrolmentHateoasAssemblerImpl assembler;
    private CourseEditionEnrolmentDto testDto;

    @BeforeEach
    void setUp() {
        // Arrange
        assembler = new CourseEditionEnrolmentHateoasAssemblerImpl();
        testDto = mock(CourseEditionEnrolmentDto.class);
    }

    @Test
    void toModel_ShouldCreateEntityModelWithCorrectDto() {
        // Arrange
        // Act
        EntityModel<CourseEditionEnrolmentDto> result = assembler.toModel(testDto);

        // Assert
        assertEquals(testDto, result.getContent());
        assertTrue(result.hasLink("delete"));
    }

    @Test
    void toModel_ShouldNotBeNull() {
        // Arrange
        // Act
        EntityModel<CourseEditionEnrolmentDto> result = assembler.toModel(testDto);

        // Assert
        assertNotNull(result);
    }
}
