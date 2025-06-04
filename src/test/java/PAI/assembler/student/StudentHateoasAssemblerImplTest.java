package PAI.assembler.student;

import PAI.controllerRest.StudentRestController;
import PAI.domain.student.Student;
import PAI.dto.student.StudentResponseDTO;
import PAI.VOs.StudentID;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

class StudentHateoasAssemblerImplTest {

    @Test
    void toModel_shouldReturnEntityModelWithLinks() {
        // Arrange
        StudentDTOAssemblerImpl mockDtoAssembler = mock(StudentDTOAssemblerImpl.class);
        StudentHateoasAssemblerImpl assembler = new StudentHateoasAssemblerImpl(mockDtoAssembler);

        Student mockStudent = mock(Student.class);
        StudentID studentID = new StudentID(1234567);

        StudentResponseDTO mockDto = new StudentResponseDTO(
                1234567, "Name", "123456789", "PT",
                "Rua", "1234-567", "Cidade", "Portugal",
                "+351", "912345678", "email@example.com", "123456789@ipt.pt"
        );

        when(mockStudent.getStudentID()).thenReturn(studentID);
        when(mockDtoAssembler.toStudentResponseDTO(mockStudent)).thenReturn(mockDto);

        // Act
        EntityModel<StudentResponseDTO> result = assembler.toModel(mockStudent);

        // Assert
        assertNotNull(result);
        assertEquals(mockDto, result.getContent());
        assertTrue(result.getLinks().hasLink("last-student-id"));

        String expectedHref = linkTo(methodOn(StudentRestController.class).getLastStudentID()).toUri().toString();
        assertEquals(expectedHref, result.getLink("last-student-id").get().getHref());

        verify(mockDtoAssembler, times(1)).toStudentResponseDTO(mockStudent);
    }
}