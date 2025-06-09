package PAI.assembler.programme;

import PAI.dto.Programme.ProgrammeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeHATEOASAssemblerTest {

    @Test
    void shouldReturnEntityModelWithLinks() {
        //arrange
        ProgrammeDTO programmeDTODouble = mock(ProgrammeDTO.class);
        ProgrammeHATEOASAssembler assembler = new ProgrammeHATEOASAssembler();

        //arrange
        EntityModel<ProgrammeDTO> result = assembler.toModel(programmeDTODouble);

        //assert
        assertEquals(result.getContent(), programmeDTODouble);
        assertTrue(result.getLink("self").isPresent());
        assertTrue(result.getLink("all").isPresent());
    }

}