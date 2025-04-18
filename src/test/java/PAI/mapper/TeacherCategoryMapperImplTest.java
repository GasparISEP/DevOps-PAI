package PAI.mapper;

import PAI.domain.TeacherCategory;
import PAI.persistence.datamodel.TeacherCategoryDataModel;
import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryMapperImplTest {

    private final ITeacherCategoryMapper mapper = new TeacherCategoryMapperImpl();

    @Test
    void shouldMapDomainToDataModel() {
        // Arrange
        UUID id = UUID.randomUUID();
        Name name = new Name("Matemática");
        TeacherCategory domain = new TeacherCategory(new TeacherCategoryID(id), name);

        // Act
        TeacherCategoryDataModel data = mapper.toDataModel(domain);

        // Assert
        assertEquals(id, data.getId());
        assertEquals("Matemática", data.getName());
    }

    @Test
    void shouldMapDataModelToDomain() {
        // Arrange
        UUID id = UUID.randomUUID();
        TeacherCategoryDataModel data = new TeacherCategoryDataModel(id, "Física");

        // Act
        TeacherCategory domain = mapper.toDomainModel(data);

        // Assert
        assertEquals(id, domain.identity().getValue());
        assertEquals("Física", domain.getName().getName());
    }
}