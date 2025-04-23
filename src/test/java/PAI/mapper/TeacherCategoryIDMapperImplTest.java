package PAI.mapper;

import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import PAI.persistence.datamodel.TeacherCategoryIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryIDMapperTest {

    // Como estamos fora do Spring, instanciamos diretamente
    private final TeacherCategoryIDMapper mapper = new TeacherCategoryIDMapper();

    @Test
    void shouldMapDomainToDataModel() {
        UUID uuid = UUID.randomUUID();
        TeacherCategoryID domainId = new TeacherCategoryID(uuid);

        TeacherCategoryIDDataModel dataModel = mapper.toDataModel(domainId);

        assertNotNull(dataModel);
        assertEquals(uuid, dataModel.getValue());
    }

    @Test
    void shouldMapDataModelToDomain() {
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel dataModel = new TeacherCategoryIDDataModel(uuid);

        TeacherCategoryID domainId = mapper.toDomainModel(dataModel);

        assertNotNull(domainId);
        assertEquals(uuid, domainId.getValue());
    }

    @Test
    void shouldThrowExceptionIfDomainIsNull() {
        assertThrows(IllegalArgumentException.class, () -> mapper.toDataModel(null));
    }

    @Test
    void shouldThrowExceptionIfDataModelIsNull() {
        assertThrows(IllegalArgumentException.class, () -> mapper.toDomainModel(null));
    }

    @Test
    void shouldThrowExceptionIfDataModelHasNullValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            TeacherCategoryIDDataModel dataModel = new TeacherCategoryIDDataModel(null);
            mapper.toDomainModel(dataModel);
        });
    }

    @Test
    void shouldThrowExceptionWhenIdAndNameAreNull() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategory(null, null));
    }


}
