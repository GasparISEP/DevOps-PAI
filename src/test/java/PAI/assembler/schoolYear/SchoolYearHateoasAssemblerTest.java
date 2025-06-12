package PAI.assembler.schoolYear;
 import PAI.dto.department.DepartmentWithDirectorDTO;
 import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 import org.springframework.hateoas.CollectionModel;
 import org.springframework.hateoas.EntityModel;

import java.time.LocalDate;
 import java.util.Iterator;
 import java.util.List;

 import static org.junit.jupiter.api.Assertions.*;
 import static org.mockito.Mockito.mock;
 import static org.mockito.Mockito.when;

class SchoolYearHateoasAssemblerTest {

    private SchoolYearHateoasAssembler assembler;

    @BeforeEach
    void setUp() {
        assembler = new SchoolYearHateoasAssembler();
    }

    @Test
    void shouldCreateEntityModelWithExpectedLinks() {
        CurrentSchoolYearDTO dto = new CurrentSchoolYearDTO("HI", "Department of Informatics", LocalDate.of(2024,12,2), LocalDate.of(2025,12,6));

        EntityModel<CurrentSchoolYearDTO> model = assembler.toModel(dto);

        assertEquals(dto, model.getContent());

        assertTrue(model.getLink("self").isPresent());
        assertTrue(model.getLink("all").isPresent());

        String selfHref = model.getLink("self").get().getHref();
        String allHref = model.getLink("all").get().getHref();

        assertTrue(selfHref.contains("/school-years/HI"));
        assertTrue(allHref.contains("/school-years"));
    }

    @Test
    void shouldCreateCollectionModelWithValidSchoolYearIdLinks() {
        // Arrange
        CurrentSchoolYearDTO dto1 = mock(CurrentSchoolYearDTO.class);
        CurrentSchoolYearDTO dto2 = mock(CurrentSchoolYearDTO.class);

        when(dto1.id()).thenReturn("d3b7e3b2-2a4f-4e79-a7a1-08c3b2d2b901");

        when(dto2.id()).thenReturn("d3b7e3b2-2a4f-4e79-a7a1-08c3b2d2b902");

        List<CurrentSchoolYearDTO> dtos = List.of(dto1, dto2);

        // Act
        CollectionModel<EntityModel<CurrentSchoolYearDTO>> result = assembler.CollectionModel(dtos);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        Iterator<EntityModel<CurrentSchoolYearDTO>> iterator = result.getContent().iterator();
        EntityModel<CurrentSchoolYearDTO> first = iterator.next();

        assertTrue(first.getLink("self").isPresent());
        assertEquals("/school-years/d3b7e3b2-2a4f-4e79-a7a1-08c3b2d2b901", first.getLink("self").get().getHref());

        EntityModel<CurrentSchoolYearDTO> second = iterator.next();
        assertTrue(second.getLink("self").isPresent());
        assertEquals("/school-years/d3b7e3b2-2a4f-4e79-a7a1-08c3b2d2b902", second.getLink("self").get().getHref());

        assertTrue(result.getLink("self").isPresent());
        assertTrue(result.getLink("self").get().getHref().contains("/school-years"));
    };

    @Test
    void shouldAnEmptyCreateCollectionModelIfThereAreNotSchoolYearsInTheSystem() {
        // Arrange
        List<CurrentSchoolYearDTO> dtos = List.of();

        // Act
        CollectionModel<EntityModel<CurrentSchoolYearDTO>> result = assembler.CollectionModel(dtos);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getContent().size());
    };
}