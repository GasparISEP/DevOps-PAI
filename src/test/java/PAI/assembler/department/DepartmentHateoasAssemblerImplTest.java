package PAI.assembler.department;

import PAI.dto.department.DepartmentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import static org.junit.jupiter.api.Assertions.*;

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
}