    package PAI.assembler.studyPlan;

    import PAI.controllerRest.ProgrammeRestController;
    import PAI.dto.studyPlan.StudyPlanResponseDTO;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.BeforeEach;
    import org.springframework.hateoas.EntityModel;

    import java.time.LocalDate;
    import java.util.UUID;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


    class StudyPlanHATEOASAssemblerImplTest {

        private StudyPlanHATEOASAssemblerImpl assembler;
        private StudyPlanResponseDTO testDto;
        private UUID studyPlanUuid;
        private String programmeAcronym;

        @BeforeEach
        void setUp() {
            assembler = new StudyPlanHATEOASAssemblerImpl();
            studyPlanUuid = UUID.fromString("0a1b2c3d-4e5f-6a7b-8c9d-0e1f2a3b4c5d");
            programmeAcronym = "ENG";

            testDto = new StudyPlanResponseDTO(
                    programmeAcronym,
                    LocalDate.of(2023, 9, 1),
                    4, // durationInYears
                    240,            // maxEcts
                    studyPlanUuid
            );
        }

        @Test
        void toModel_shouldNotReturnNull() {
            // Arrange
            // Act
            EntityModel<StudyPlanResponseDTO> entityModel = assembler.toModel(testDto);

            // Assert
            assertNotNull(entityModel, "EntityModel should not be null.");
        }

        @Test
        void toModel_shouldWrapCorrectContent() {
            // Arrange
            // Act
            EntityModel<StudyPlanResponseDTO> entityModel = assembler.toModel(testDto);

            // Assert
            assertEquals(testDto, entityModel.getContent(), "EntityModel should wrap the correct StudyPlanResponseDTO.");
        }

        @Test
        void toModel_shouldIncludeSelfLink() {
            // Arrange
            // Act
            EntityModel<StudyPlanResponseDTO> entityModel = assembler.toModel(testDto);

            // Assert
            assertTrue(entityModel.hasLink("self"), "EntityModel should contain a 'self' link.");
        }

        @Test
        void toModel_selfLinkHrefShouldMatchExpectedUri() {
            // Arrange
            String expectedSelfUri = linkTo(methodOn(ProgrammeRestController.class).getStudyPlanByGeneratedID(studyPlanUuid))
                    .toUri()
                    .toString();

            // Act
            EntityModel<StudyPlanResponseDTO> entityModel = assembler.toModel(testDto);

            // Assert
            assertEquals(expectedSelfUri, entityModel.getRequiredLink("self").getHref(), "The 'self' link's href should match the expected URI.");
        }

        @Test
        void toModel_shouldIncludeProgrammeLink() {
            // Arrange
            // Act
            EntityModel<StudyPlanResponseDTO> entityModel = assembler.toModel(testDto);

            // Assert
            assertTrue(entityModel.hasLink("programme"), "EntityModel should contain a 'programme' link.");
        }

        @Test
        void toModel_programmeLinkHrefShouldMatchExpectedUri() {
            // Arrange
            String expectedProgrammeUri = linkTo(methodOn(ProgrammeRestController.class).getProgrammeByID(programmeAcronym))
                    .toUri()
                    .toString();

            // Act
            EntityModel<StudyPlanResponseDTO> entityModel = assembler.toModel(testDto);

            // Assert
            assertEquals(expectedProgrammeUri, entityModel.getRequiredLink("programme").getHref(), "The 'programme' link's href should match the expected URI.");
        }

        @Test
        void toModel_programmeLinkRelShouldBeProgramme() {
            // Arrange
            // Act
            EntityModel<StudyPlanResponseDTO> entityModel = assembler.toModel(testDto);

            // Assert
            assertEquals("programme", entityModel.getRequiredLink("programme").getRel().value(), "The 'programme' link's rel should be 'programme'.");
        }
    }