package PAI.assembler.degreeType;

import PAI.dto.degreeType.DegreeTypeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeHATEOASAssemblerTest {

    @Test
    void shouldReturnEntityModelWithLinks() {
        // arrange
        DegreeTypeDTO dto = new DegreeTypeDTO("123", "Bachelor", 180);
        DegreeTypeHATEOASAssembler assembler = new DegreeTypeHATEOASAssembler();

        // act
        EntityModel<DegreeTypeDTO> result = assembler.toModel(dto);

        // assert
        assertEquals(dto, result.getContent());
        assertTrue(result.getLink("self").isPresent(), "Self link should be present");
        assertTrue(result.getLink("all-degree-types").isPresent(), "All-degree-types link should be present");
    }

}