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

        // Print links for debug
        System.out.println(result.getLinks());

        // Verifica link view-details
        assertTrue(result.getLinks().hasLink("view-details"), "Missing 'view-details' link");
        String expectedViewDetailsHref = linkTo(methodOn(StudentRestController.class)
                .getStudentByID(dto.getStudentID()))
                .toUri()
                .toString();
        assertEquals(expectedViewDetailsHref,
                result.getLink("view-details").get().getHref());

        // Verifica link all-students
        assertTrue(result.getLinks().hasLink("all-students"), "Missing 'all-students' link");
        String expectedAllStudentsHref = linkTo(methodOn(StudentRestController.class)
                .getAllStudents())
                .toUri()
                .toString();
        assertEquals(expectedAllStudentsHref,
                result.getLink("all-students").get().getHref());

        // Verifica link self
        assertTrue(result.getLinks().hasLink("self"), "Missing 'self' link");
        String expectedSelfHref = linkTo(methodOn(StudentRestController.class)
                .getStudentByID(dto.getStudentID()))
                .toUri()
                .toString();
        assertEquals(expectedSelfHref,
                result.getLink("self").get().getHref());

        // Verifica link enrol-in-programme
        assertTrue(result.getLinks().hasLink("enrol-in-programme"), "Missing 'enrol-in-programme' link");
        String expectedEnrolHref = linkTo(methodOn(StudentRestController.class)
                .enrolStudentInProgramme(null))
                .toUri()
                .toString();
        assertEquals(expectedEnrolHref,
                result.getLink("enrol-in-programme").get().getHref());
    }
}