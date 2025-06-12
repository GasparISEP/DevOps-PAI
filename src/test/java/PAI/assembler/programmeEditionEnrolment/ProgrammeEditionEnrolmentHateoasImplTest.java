package PAI.assembler.programmeEditionEnrolment;

import PAI.controllerRest.StudentProgrammeEditionEnrolmentRestController;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ProgrammeEditionEnrolmentHateoasImplTest {

    private final ProgrammeEditionEnrolmentHateoasImpl assembler = new ProgrammeEditionEnrolmentHateoasImpl();
    private static final String SELF_REL = "self";
    private static final String COURSE_EDITIONS_REL = "get-all-course-editions";

    @Test
    void toModel_ShouldCreateEntityModelWithCorrectLinks() {
        // Arrange
        ProgrammeEditionEnrolmentDetailDto dto = mock(ProgrammeEditionEnrolmentDetailDto.class);
        when(dto.studentID()).thenReturn(123);
        
        // Act
        EntityModel<ProgrammeEditionEnrolmentDetailDto> result = assembler.toModel(dto);
        
        // Assert
        assertNotNull(result);
        assertEquals(dto, result.getContent());
        assertTrue(result.getLink("get-programme-edition-enrolments-by-student-id").isPresent());
    }

    @Test
    void toCollectionModel_ShouldCreateCollectionModelWithCorrectLinks() {
        // Arrange
        ProgrammeEditionEnrolmentDetailDto dto1 = mock(ProgrammeEditionEnrolmentDetailDto.class);
        ProgrammeEditionEnrolmentDetailDto dto2 = mock(ProgrammeEditionEnrolmentDetailDto.class);
        when(dto1.studentID()).thenReturn(123);
        when(dto2.studentID()).thenReturn(456);
        
        List<ProgrammeEditionEnrolmentDetailDto> dtos = Arrays.asList(dto1, dto2);
        
        // Act
        CollectionModel<EntityModel<ProgrammeEditionEnrolmentDetailDto>> result = assembler.toCollectionModel(dtos);
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        
        // Check that each entity has both self and course-editions links
        result.getContent().forEach(entityModel -> {
            assertTrue(entityModel.getLink(SELF_REL).isPresent(), "Self link should be present");
            assertTrue(entityModel.getLink(COURSE_EDITIONS_REL).isPresent(), "Course editions link should be present");
        });
    }

    @Test
    void toCollectionModel_WithEmptyList_ShouldReturnEmptyCollection() {
        // Arrange
        List<ProgrammeEditionEnrolmentDetailDto> emptyList = List.of();
        
        // Act
        CollectionModel<EntityModel<ProgrammeEditionEnrolmentDetailDto>> result = assembler.toCollectionModel(emptyList);
        
        // Assert
        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
    }
}
