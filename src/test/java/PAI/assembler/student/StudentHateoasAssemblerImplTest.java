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

        // Verifica link viewDetails (agora relativo)
        assertTrue(result.getLinks().hasLink("viewDetails"));
        assertEquals("/students/" + dto.getStudentID(),
                result.getLink("viewDetails").get().getHref());

        // Verifica link viewAll (agora relativo)
        assertTrue(result.getLinks().hasLink("viewAll"));
        assertEquals("/students/display",
                result.getLink("viewAll").get().getHref());

        // Verifica link self (gerado com linkTo)
        assertTrue(result.getLinks().hasLink("self"));
        String expectedSelfHref = linkTo(methodOn(StudentRestController.class)
                .getStudentByID(dto.getStudentID()))
                .toUri()
                .toString();
        assertEquals(expectedSelfHref,
                result.getLink("self").get().getHref());

        // Verifica link enrol-in-programme
        assertTrue(result.getLinks().hasLink("enrol-in-programme"));
        String expectedEnrolHref = linkTo(methodOn(StudentRestController.class)
                .enrolStudentInProgramme(null))
                .toUri()
                .toString();
        assertEquals(expectedEnrolHref,
                result.getLink("enrol-in-programme").get().getHref());
    }
}