package PAI.assembler.programme;

import PAI.dto.Programme.ProgrammeDirectorResponseDTO;
import PAI.dto.teacher.TeacherIdDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeDirectorHateoasAssemblerTest {

    private ProgrammeDirectorHateoasAssembler assembler;

    @BeforeEach
    void setUp() {
        assembler = new ProgrammeDirectorHateoasAssembler();
    }

    @Test
    void shouldReturnCollectionModelWithTeachersAndSelfLink() {
        // Arrange
        String programmeAcronym = "LEI";
        TeacherIdDTO teacher1 = new TeacherIdDTO("ABC");
        TeacherIdDTO teacher2 = new TeacherIdDTO("XYZ");
        ProgrammeDirectorResponseDTO dto = new ProgrammeDirectorResponseDTO(List.of(teacher1, teacher2));

        // Act
        CollectionModel<TeacherIdDTO> model = assembler.toModel(programmeAcronym, dto);

        // Assert content
        Set<String> acronyms = model.getContent().stream()
                .map(TeacherIdDTO::acronym)
                .collect(Collectors.toSet());
        assertEquals(Set.of("ABC", "XYZ"), acronyms);

        // Assert HATEOAS self link
        Link selfLink = model.getLink("self").orElse(null);
        assertNotNull(selfLink);
        assertTrue(selfLink.getHref().contains("/programmes/" + programmeAcronym + "/director"));
    }
}