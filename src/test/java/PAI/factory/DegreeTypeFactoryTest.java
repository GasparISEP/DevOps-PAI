package PAI.factory;

import PAI.domain.DegreeType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class DegreeTypeFactoryTest {

    @Test
    void addNewDegreeType() throws Exception{

        //Arrange
        String name = "Master";
        int maxEcts = 200;

        try(MockedConstruction<DegreeType> mockConstruction =  mockConstruction(DegreeType.class,(mock, context) -> {
            String nomeAtual = (String) context.arguments().get(0);
            int valorAtual = (int) context.arguments().get(1);

            when(mock.get_name()).thenReturn(nomeAtual);
            when(mock.get_maxEcts()).thenReturn(valorAtual);

    } )) {

            // act
            DegreeTypeFactory factory = new DegreeTypeFactory();
            DegreeType degreeType = factory.addNewDegreeType(name, maxEcts);

            // Assert
            assertEquals(1, mockConstruction.constructed().size());
            DegreeType createdDegreeType = mockConstruction.constructed().get(0);

            assertEquals(name, createdDegreeType.get_name());
            assertEquals(maxEcts, createdDegreeType.get_maxEcts());


        }
        }
}