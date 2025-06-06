package PAI.assembler.department;

import PAI.dto.department.DepartmentWithDirectorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentWithDirectorHateoasAssemblerImplTest {

    private DepartmentWithDirectorHateoasAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        assembler = new DepartmentWithDirectorHateoasAssemblerImpl();
    }

    @Test
    void shouldCreateEntityModelWithExpectedLinks() {
        DepartmentWithDirectorDTO dto = new DepartmentWithDirectorDTO("AAA", "Astronomia", "AAA", "AAA");

        EntityModel<DepartmentWithDirectorDTO> model = assembler.toModel(dto);

        assertEquals(dto, model.getContent());

        assertTrue(model.getLink("self").isPresent());
        assertTrue(model.getLink("all-departments").isPresent());
        assertTrue(model.getLink("update-director").isPresent());

        String selfHref = model.getLink("self").get().getHref();
        String allHref = model.getLink("all-departments").get().getHref();
        String updateHref = model.getLink("update-director").get().getHref();

        assertTrue(selfHref.contains("/departments/AAA"));
        assertTrue(allHref.contains("/departments"));
        assertTrue(updateHref.contains("/departments/AAA/director"));
    }
}
