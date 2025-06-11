package PAI.assembler.teacherCareerProgression;

import PAI.dto.teacherCareerProgression.UpdateTeacherWorkingPercentageResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTeacherWorkingPercentageHateoasAssemblerTest {

    @Test
    void shouldAddAllLinks() {
        // Arrange
        var dto = new UpdateTeacherWorkingPercentageResponseDTO(
                "123456789", "2024-06-01", "AAA", "CAT1", 100
        );
        var assembler = new UpdateTeacherWorkingPercentageHateoasAssembler();

        // Act
        EntityModel<UpdateTeacherWorkingPercentageResponseDTO> model = assembler.toModel(dto);

        // Assert
        assertEquals(dto, model.getContent());
        assertTrue(model.getLink("self").isPresent());
        assertTrue(model.getLink("all").isPresent());

        // Verifica se o link self contém o ID correto
        String progressionId = dto.teacherCareerProgressionId();
        assertTrue(model.getLink("self").get().getHref().contains(progressionId));
    }
}
