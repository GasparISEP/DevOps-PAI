//package PAI.assembler.student;
//
//import PAI.controllerRest.StudentRestController;
//import PAI.dto.student.StudentResponseDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.hateoas.EntityModel;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
//
//class StudentHateoasAssemblerImplTest {
//
//    @Test
//    void toModel_shouldReturnEntityModelWithCorrectLinks() {
//        // Arrange
//        StudentHateoasAssemblerImpl assembler = new StudentHateoasAssemblerImpl();
//
//        StudentResponseDTO dto = new StudentResponseDTO(
//                1234567, "João Silva", "123456789", "Portugal",
//                "Rua Central", "1234-567", "Porto", "Portugal",
//                "+351", "912345678", "joao.silva@example.com", "1234567@ipt.pt"
//        );
//
//        // Act
//        EntityModel<StudentResponseDTO> result = assembler.toModel(dto);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(dto, result.getContent());
//
//        // Verificar link "last-student-id"
//        assertTrue(result.getLinks().hasLink("last-student-id"));
//        String expectedHref1 = linkTo(methodOn(StudentRestController.class).getLastStudentID()).toUri().toString();
//        assertEquals(expectedHref1, result.getLink("last-student-id").get().getHref());
//
//        // Verificar link "all"
//        assertTrue(result.getLinks().hasLink("all"));
//        String expectedHref2 = linkTo(methodOn(StudentRestController.class).getAllStudents()).toUri().toString();
//        assertEquals(expectedHref2, result.getLink("all").get().getHref());
//    }
//}