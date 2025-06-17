package PAI.assembler.student;

import PAI.controllerRest.StudentRestController;
import PAI.dto.student.StudentResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

class StudentHateoasAssemblerImplTest {

    @Test
    void toModel_shouldReturnEntityModelWithCorrectLinks() {
        // Arrange
        StudentHateoasAssemblerImpl assembler = new StudentHateoasAssemblerImpl();

        StudentResponseDTO dto = new StudentResponseDTO(
                1234567, "João Silva", "123456789", "Portugal",
                "Rua Central", "1234-567", "Porto", "Portugal",
                "+351", "912345678", "joao.silva@example.com", "1234567@ipt.pt"
        );

        // Act
        EntityModel<StudentResponseDTO> result = assembler.toModel(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto, result.getContent());

        assertTrue(result.getLinks().hasLink("viewDetails"));
        String expectedViewDetailsHref = "http://localhost:3000/students/" + dto.getStudentID();
        assertEquals(expectedViewDetailsHref, result.getLink("viewDetails").get().getHref());

        assertTrue(result.getLinks().hasLink("viewAll"));
        String expectedViewAllHref = "http://localhost:3000/students/display";
        assertEquals(expectedViewAllHref, result.getLink("viewAll").get().getHref());

        assertTrue(result.getLinks().hasLink("self"));
        String expectedSelfHref = linkTo(methodOn(StudentRestController.class)
                .getStudentByID(dto.getStudentID()))
                .toUri()
                .toString();
        assertEquals(expectedSelfHref, result.getLink("self").get().getHref());
    }
}