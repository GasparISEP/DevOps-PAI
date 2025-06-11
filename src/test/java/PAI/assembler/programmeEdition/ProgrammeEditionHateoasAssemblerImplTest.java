package PAI.assembler.programmeEdition;

import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionHateoasAssemblerImplTest {

    @Test
    void shouldCreateEntityModelWithNonNullContent() throws Exception {
        // arrange
        ProgrammeEditionHateoasAssemblerImpl hateoasAssembler = new ProgrammeEditionHateoasAssemblerImpl();
        ProgrammeEditionResponseDTO dto = new ProgrammeEditionResponseDTO(
                new ProgrammeIDDTO("ENG"), "2024"
        );

        // act
        EntityModel<ProgrammeEditionResponseDTO> result = hateoasAssembler.toModel(dto);

        // assert
        assertNotNull(result);
        assertNotNull(result.getContent());
    }

    @Test
    void shouldContainCorrectContent() throws Exception {
        // arrange
        ProgrammeEditionHateoasAssemblerImpl hateoasAssembler = new ProgrammeEditionHateoasAssemblerImpl();
        ProgrammeEditionResponseDTO dto = new ProgrammeEditionResponseDTO(
                new ProgrammeIDDTO("ENG"), "2024"
        );

        // act
        EntityModel<ProgrammeEditionResponseDTO> result = hateoasAssembler.toModel(dto);

        // assert
        assertEquals(dto, result.getContent());
    }

    @Test
    void shouldContainAllExpectedLinks() throws Exception {
        // arrange
        ProgrammeEditionHateoasAssemblerImpl hateoasAssembler = new ProgrammeEditionHateoasAssemblerImpl();
        ProgrammeEditionResponseDTO dto = new ProgrammeEditionResponseDTO(
                new ProgrammeIDDTO("ENG"), "2024"
        );

        // act
        EntityModel<ProgrammeEditionResponseDTO> result = hateoasAssembler.toModel(dto);

        // assert
        assertTrue(result.getLink("self").isPresent());
        assertTrue(result.getLink("collection").isPresent());
        assertTrue(result.getLink("numberOfStudents").isPresent());
    }

    @Test
    void shouldThrowRuntimeExceptionWhenProgrammeEditionResponseDTOIsNull() {
        // arrange
        ProgrammeEditionHateoasAssemblerImpl hateoasAssembler = new ProgrammeEditionHateoasAssemblerImpl();

        // act & assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            hateoasAssembler.toModel(null);
        });

        assertTrue(exception.getMessage().contains("ProgrammeEditionResponseDTO"));
    }





}