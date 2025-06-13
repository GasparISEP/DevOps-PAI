package PAI.assembler.programme;

import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeIDDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeHATEOASAssemblerTest {

    @Test
    void shouldReturnEntityModelWithLinks() {
        //arrange
        ProgrammeIDDTO programmeIDDTODouble = mock(ProgrammeIDDTO.class);
        ProgrammeHATEOASAssembler assembler = new ProgrammeHATEOASAssembler();

        //arrange
        EntityModel<ProgrammeIDDTO> result = assembler.toModel(programmeIDDTODouble);

        //assert
        assertEquals(result.getContent(), programmeIDDTODouble);
        assertTrue(result.getLink("self").isPresent());
        assertTrue(result.getLink("all").isPresent());
    }

}