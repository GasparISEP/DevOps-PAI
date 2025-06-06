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
                "2024-06-01", "AAA", "CAT1", 100
        );
        var assembler = new UpdateTeacherWorkingPercentageHateoasAssembler();

        // Act
        EntityModel<UpdateTeacherWorkingPercentageResponseDTO> model = assembler.toModel(dto);

        // Assert
        assertEquals(dto, model.getContent());
        assertTrue(model.getLink("get-teacher").isPresent());
        assertTrue(model.getLink("all-teachers").isPresent());
        assertTrue(model.getLink("self").isPresent());

        // Verifica se os links contêm o ID correto
        String teacherId = dto.teacherID();
        assertTrue(model.getLink("get-teacher").get().getHref().contains(teacherId));
        assertTrue(model.getLink("self").get().getHref().contains(teacherId));
    }
}
