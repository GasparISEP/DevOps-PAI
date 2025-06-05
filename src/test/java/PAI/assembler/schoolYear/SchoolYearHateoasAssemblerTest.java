package PAI.assembler.schoolYear;
 import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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

}