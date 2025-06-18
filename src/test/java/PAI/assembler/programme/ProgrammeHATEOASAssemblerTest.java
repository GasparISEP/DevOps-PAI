package PAI.assembler.programme;

import PAI.controllerRest.ProgrammeRestController;
import PAI.dto.Programme.ProgrammeIDDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class ProgrammeHATEOASAssemblerTest {

    @Test
    void shouldReturnEntityModelWithLinks() {
        // Arrange
        final String TEST_ACRONYM = "PROG1";
        ProgrammeIDDTO programmeIDDTODouble = mock(ProgrammeIDDTO.class);

        when(programmeIDDTODouble.acronym()).thenReturn(TEST_ACRONYM);

        ProgrammeHATEOASAssembler assembler = new ProgrammeHATEOASAssembler();

        // Act
        EntityModel<ProgrammeIDDTO> result = assembler.toModel(programmeIDDTODouble);

        // Assert
        assertEquals(result.getContent(), programmeIDDTODouble);

        // 'self' link
        assertTrue(result.getLink("self").isPresent());
        Link selfLink = result.getLink("self").get();
        assertEquals(linkTo(methodOn(ProgrammeRestController.class).getProgrammeByID(TEST_ACRONYM)).toUri().toString(), selfLink.toUri().toString());

        // 'all' link
        assertTrue(result.getLink("all").isPresent());
        Link allLink = result.getLink("all").get();
        assertEquals(linkTo(methodOn(ProgrammeRestController.class).getAllProgrammes()).toUri().toString(), allLink.toUri().toString());

        // 'register-study-plan' link
        assertTrue(result.getLink("registerStudyPlan").isPresent());
        Link registerStudyPlanLink = result.getLink("registerStudyPlan").get();

        // 'study-plans' link
        assertTrue(result.getLink("study-plans").isPresent());
        Link studyPlansLink = result.getLink("study-plans").get();
        assertEquals(linkTo(methodOn(ProgrammeRestController.class).getStudyPlansForProgramme(TEST_ACRONYM)).toUri().toString(), studyPlansLink.toUri().toString());
    }
}