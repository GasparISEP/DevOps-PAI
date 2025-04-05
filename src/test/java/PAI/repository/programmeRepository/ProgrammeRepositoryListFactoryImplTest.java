package PAI.repository.programmeRepository;


import PAI.domain.programme.Programme;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeRepositoryListFactoryImplTest {
    @Test
    void shouldCreateRepositoryListFactoryImpl(){
        ProgrammeDDDRepositoryListFactoryImpl programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        //act
        List<Programme> programmeList = programmeRepositoryListFactory.newProgrammeArrayList();
        //assert
        assertNotNull(programmeList);
        assertInstanceOf(ArrayList.class, programmeList);
    }

    @Test
    void whenCopyProgrammeArrayListInvoked_ThenShouldReturnNewIndependentCopy() {
        // arrange
        ProgrammeDDDRepositoryListFactoryImpl factory = new ProgrammeDDDRepositoryListFactoryImpl();

        Programme mockProgramme1 = mock(Programme.class);
        Programme mockProgramme2 = mock(Programme.class);

        List<Programme> originalList = new ArrayList<>();
        originalList.add(mockProgramme1);
        originalList.add(mockProgramme2);

        // act
        List<Programme> copiedList = factory.copyProgrammeArrayList(originalList);

        // assert
        assertNotSame(originalList, copiedList); // verifica se é uma nova instância
        assertEquals(originalList.size(), copiedList.size()); // verifica se têm o mesmo tamanho
        assertIterableEquals(originalList, copiedList); // verifica se os elementos são os mesmos (por igualdade de referência ou equals)
    }
}