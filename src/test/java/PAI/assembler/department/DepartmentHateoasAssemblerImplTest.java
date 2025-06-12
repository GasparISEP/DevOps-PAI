package PAI.assembler.department;

import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.DepartmentWithDirectorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentHateoasAssemblerImplTest {

    private DepartmentHateoasAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        assembler = new DepartmentHateoasAssemblerImpl();
    }

    @Test
    void shouldCreateEntityModelWithExpectedLinks() {
        DepartmentDTO dto = new DepartmentDTO("DEI");

        EntityModel<DepartmentDTO> model = assembler.toModel(dto);

        assertEquals(dto, model.getContent());

        assertTrue(model.getLink("self").isPresent());
        assertTrue(model.getLink("all").isPresent());

        String selfHref = model.getLink("self").get().getHref();
        String allHref = model.getLink("all").get().getHref();

        assertTrue(selfHref.contains("/departments/DEI"));
        assertTrue(allHref.contains("/departments"));
    }

    @Test
    void shouldCreateCollectionModelWithValidDirectorLinks() {
        // Arrange
        DepartmentWithDirectorDTO dto1 = mock(DepartmentWithDirectorDTO.class);
        DepartmentWithDirectorDTO dto2 = mock(DepartmentWithDirectorDTO.class);

        when(dto1.id()).thenReturn("dep1");
        when(dto1.teacherID()).thenReturn("teacher1");

        when(dto2.id()).thenReturn("dep2");
        when(dto2.teacherID()).thenReturn("teacher2");

        List<DepartmentWithDirectorDTO> dtos = List.of(dto1, dto2);

        // Act
        CollectionModel<EntityModel<DepartmentWithDirectorDTO>> result = assembler.toDiretorCollectionModel(dtos);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        Iterator<EntityModel<DepartmentWithDirectorDTO>> iterator = result.getContent().iterator();
        EntityModel<DepartmentWithDirectorDTO> first = iterator.next();

        assertTrue(first.getLink("self").isPresent());
        assertEquals("/departments/dep1", first.getLink("self").get().getHref());

        assertTrue(first.getLink("director").isPresent());
        assertEquals("/teachers/teacher1", first.getLink("director").get().getHref());

        EntityModel<DepartmentWithDirectorDTO> second = iterator.next();
        assertTrue(second.getLink("self").isPresent());
        assertEquals("/departments/dep2", second.getLink("self").get().getHref());
        assertTrue(second.getLink("director").isPresent());
        assertEquals("/teachers/teacher2", second.getLink("director").get().getHref());

        assertTrue(result.getLink("self").isPresent());
        assertTrue(result.getLink("self").get().getHref().contains("/departments"));
        };

    @Test
    void shouldCreateCollectionModelWithSecondDepartmentWithoutDirectorLink() {
        // Arrange
        DepartmentWithDirectorDTO dto1 = mock(DepartmentWithDirectorDTO.class);
        DepartmentWithDirectorDTO dto2 = mock(DepartmentWithDirectorDTO.class);

        when(dto1.id()).thenReturn("dep1");
        when(dto1.teacherID()).thenReturn("teacher1");

        when(dto2.id()).thenReturn("dep2");
        when(dto2.teacherID()).thenReturn(null);

        List<DepartmentWithDirectorDTO> dtos = List.of(dto1, dto2);

        // Act
        CollectionModel<EntityModel<DepartmentWithDirectorDTO>> result = assembler.toDiretorCollectionModel(dtos);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        Iterator<EntityModel<DepartmentWithDirectorDTO>> iterator = result.getContent().iterator();
        EntityModel<DepartmentWithDirectorDTO> first = iterator.next();

        assertTrue(first.getLink("self").isPresent());
        assertEquals("/departments/dep1", first.getLink("self").get().getHref());

        assertTrue(first.getLink("director").isPresent());
        assertEquals("/teachers/teacher1", first.getLink("director").get().getHref());

        EntityModel<DepartmentWithDirectorDTO> second = iterator.next();
        assertTrue(second.getLink("self").isPresent());
        assertEquals("/departments/dep2", second.getLink("self").get().getHref());
        assertTrue(second.getLink("director").isEmpty());

        assertTrue(result.getLink("self").isPresent());
        assertTrue(result.getLink("self").get().getHref().contains("/departments"));
    };
}