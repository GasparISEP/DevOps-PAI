package PAI.mapper;

import PAI.domain.TeacherCategory;
import PAI.persistence.datamodel.TeacherCategoryDataModel;
import PAI.persistence.datamodel.TeacherCategoryIDDataModel;
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
        UUID uuid = UUID.randomUUID();
        Name name = new Name("Catedrático");
        TeacherCategoryID domainId = new TeacherCategoryID(uuid);
        TeacherCategory domain = new TeacherCategory(domainId, name);

        // Act
        TeacherCategoryDataModel data = mapper.toDataModel(domain);

        // Assert
        assertEquals(uuid, data.getId().getValue()); // now uses EmbeddedId
        assertEquals("Catedrático", data.getName());
    }

    @Test
    void shouldMapDataModelToDomain() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel dataId = new TeacherCategoryIDDataModel(uuid);
        TeacherCategoryDataModel data = new TeacherCategoryDataModel(dataId, "Auxiliar");

        // Act
        TeacherCategory domain = mapper.toDomainModel(data);

        // Assert
        assertEquals(uuid, domain.identity().getValue()); // now compare inner UUID
        assertEquals("Auxiliar", domain.getName().getName());
    }
}
