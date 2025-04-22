package PAI.mapper;

import PAI.VOs.TeacherCareerProgressionID;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCareerProgressionIDMapperTest {

    @Test
    void shouldReturnTeacherCareerProgressionIDDataModelWhenDomainToDataModelIsCalled(){
        // Arrange
        TeacherCareerProgressionIDMapper mapper = new TeacherCareerProgressionIDMapper();
        TeacherCareerProgressionID domainIDDouble = mock(TeacherCareerProgressionID.class);
        UUID idDouble = mock(UUID.class);
        when(domainIDDouble.getIDValue()).thenReturn(idDouble);

        try (MockedConstruction<TeacherCareerProgressionIDDataModel> constructerDouble = mockConstruction(
                TeacherCareerProgressionIDDataModel.class, (mock, context) -> {

                when(mock.getIDValue()).thenReturn(idDouble);
            })) {

            // Act
            TeacherCareerProgressionIDDataModel result = mapper.domainToDataModel(domainIDDouble);

            // Assert
            assertEquals(1, constructerDouble.constructed().size());
            assertSame(constructerDouble.constructed().get(0), result);
            assertEquals(idDouble, result.getIDValue());
        }
    }

    @Test
    void shouldThrowNullPointerExceptionWhenConstructorFailsInDomainToDataModel() {
        // Arrange
        TeacherCareerProgressionIDMapper mapper = new TeacherCareerProgressionIDMapper();
        TeacherCareerProgressionID domainIDDouble = mock(TeacherCareerProgressionID.class);
        when(domainIDDouble.getIDValue()).thenReturn(null);

        try (MockedConstruction<TeacherCareerProgressionIDDataModel> constructerDouble = mockConstruction(
                TeacherCareerProgressionIDDataModel.class, invocation -> {

                throw new NullPointerException("ID can not be null!");
            })) {

            // Act & Assert
            assertThrows(NullPointerException.class, () -> mapper.domainToDataModel(domainIDDouble));
        }
    }

    @Test
    void shouldReturnTeacherCareerProgressionIDWhenDataModelToDomainIsCalled() {
        // Arrange
        TeacherCareerProgressionIDMapper mapper = new TeacherCareerProgressionIDMapper();
        TeacherCareerProgressionIDDataModel dataModelIdDouble = mock(TeacherCareerProgressionIDDataModel.class);
        UUID idDouble = mock(UUID.class);

        when(dataModelIdDouble.getIDValue()).thenReturn(idDouble);

        try (MockedConstruction<TeacherCareerProgressionID> constructerDouble = mockConstruction(
                TeacherCareerProgressionID.class, (mock, context) -> {

                    when(mock.getIDValue()).thenReturn(idDouble);
                })) {

            // Act
            TeacherCareerProgressionID result = mapper.dataModelToDomain(dataModelIdDouble);

            // Assert
            assertEquals(1, constructerDouble.constructed().size());
            assertSame(constructerDouble.constructed().get(0), result);
            assertEquals(idDouble, result.getIDValue());
        }
    }

    @Test
    void shouldThrowNullPointerExceptionWhenConstructorFailsInDataModelToDomain() {
        // Arrange
        TeacherCareerProgressionIDMapper mapper = new TeacherCareerProgressionIDMapper();
        TeacherCareerProgressionIDDataModel dataModelIdDouble = mock(TeacherCareerProgressionIDDataModel.class);
        when(dataModelIdDouble.getIDValue()).thenReturn(null);

        try (MockedConstruction<TeacherCareerProgressionID> constructerDouble = mockConstruction(
                TeacherCareerProgressionID.class, invocation -> {

                    throw new NullPointerException("Id can not be null!");
                })) {

            // Act & Assert
            assertThrows(NullPointerException.class, () -> mapper.dataModelToDomain(dataModelIdDouble));
        }
    }
}