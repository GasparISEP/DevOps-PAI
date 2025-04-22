package PAI.mapper;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import PAI.persistence.datamodel.TeacherCategoryDataModel;
import PAI.persistence.datamodel.TeacherCategoryIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCategoryMapperImplTest {

    private ITeacherCategoryIDMapper idMapper;
    private ITeacherCategoryMapper mapper;

    @BeforeEach
    void setUp() {
        idMapper = mock(ITeacherCategoryIDMapper.class);
        mapper = new TeacherCategoryMapperImpl(idMapper);
    }

    @Test
    void shouldMapDomainToDataModel() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryID domainId = new TeacherCategoryID(uuid);
        Name name = new Name("Catedrático");
        TeacherCategory domain = new TeacherCategory(domainId, name);

        TeacherCategoryIDDataModel expectedIdDataModel = new TeacherCategoryIDDataModel(uuid);
        when(idMapper.toDataModel(domainId)).thenReturn(expectedIdDataModel);

        // Act
        TeacherCategoryDataModel result = mapper.toDataModel(domain);

        // Assert
        assertEquals(expectedIdDataModel, result.getId(), "ID deve ser mapeado corretamente");
        assertEquals("Catedrático", result.getName(), "Nome deve ser mapeado corretamente");
    }

    @Test
    void shouldMapDataModelToDomain() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel dataId = new TeacherCategoryIDDataModel(uuid);
        TeacherCategoryDataModel data = new TeacherCategoryDataModel(dataId, "Assistente");

        TeacherCategoryID expectedDomainId = new TeacherCategoryID(uuid);
        when(idMapper.toDomainModel(dataId)).thenReturn(expectedDomainId);

        // Act
        TeacherCategory result = mapper.toDomainModel(data);

        // Assert
        assertEquals(expectedDomainId, result.identity(), "ID deve ser restaurado corretamente");
        assertEquals("Assistente", result.getName().getName(), "Nome deve ser restaurado corretamente");
    }

    @Test
    void toDataModelShouldThrowIfDomainIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> mapper.toDataModel(null),
                "Deve lançar IllegalArgumentException se o domínio for null");
    }

    @Test
    void toDomainModelShouldThrowIfDataIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> mapper.toDomainModel(null),
                "Deve lançar IllegalArgumentException se o data model for null");
    }

    @Test
    void toDomainModelShouldThrowIfIdIsNull() {
        // Arrange
        TeacherCategoryDataModel model = mock(TeacherCategoryDataModel.class);
        when(model.getId()).thenReturn(null);
        when(model.getName()).thenReturn("Auxiliar");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> mapper.toDomainModel(model),
                "Deve lançar IllegalArgumentException se o ID for null");
    }

    @Test
    void toDomainModelShouldThrowIfNameIsNull() {
        // Arrange
        TeacherCategoryDataModel model = mock(TeacherCategoryDataModel.class);
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());
        when(model.getId()).thenReturn(id);
        when(model.getName()).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> mapper.toDomainModel(model),
                "Deve lançar IllegalArgumentException se o nome for null");
    }

}
