package PAI.assembler.programmeEnrolment;

import PAI.controllerRest.StudentRestController;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

class ProgrammeEnrolmentHATEOASAssemblerTest {

    private ProgrammeEnrolmentHATEOASAssembler assembler;

    @BeforeEach
    void setUp() {
        assembler = new ProgrammeEnrolmentHATEOASAssembler();
    }

    @Test
    void toModel_ShouldContainSelfLinkPointingToGetEnrolmentEndpoint() {
        // Arrange
        UUID exampleUUID            = UUID.randomUUID();
        int exampleStudentId        = 123;
        String exampleAccessMethod  = "WEB";
        String exampleProgrammeAcr  = "CS101";
        LocalDate exampleDate       = LocalDate.of(2025, 6, 8);

        ProgrammeEnrolmentResponseDTO responseDTO =
                new ProgrammeEnrolmentResponseDTO(
                        exampleUUID,
                        exampleStudentId,
                        exampleAccessMethod,
                        exampleProgrammeAcr,
                        exampleDate
                );

        // Act
        EntityModel<ProgrammeEnrolmentResponseDTO> model =
                assembler.toModel(responseDTO);

        Link selfLink = model.getLink("self")
                .orElseThrow(() -> new AssertionError("Link 'self' não foi encontrado"));

        // esperado: GET /students/enrollStudent/{programmeEnrolmentGID}
        String expectedUri = linkTo(
                methodOn(StudentRestController.class)
                        .getEnrolmentByStudentAndProgramme(exampleUUID)
        ).toUri().toString();

        // Assert
        assertEquals(expectedUri, selfLink.getHref());
    }


}