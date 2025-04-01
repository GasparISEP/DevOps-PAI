package PAI.repository.programmeRepo;


import PAI.domain.programme.ProgrammeDDD;
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
        List<ProgrammeDDD> programmeList = programmeRepositoryListFactory.newProgrammeArrayList();
        //assert
        assertNotNull(programmeList);
        assertInstanceOf(ArrayList.class, programmeList);
    }

    @Test
    void whenCopyProgrammeArrayListInvoked_ThenShouldReturnNewIndependentCopy() {
        // arrange
        ProgrammeDDDRepositoryListFactoryImpl factory = new ProgrammeDDDRepositoryListFactoryImpl();

        ProgrammeDDD mockProgramme1 = mock(ProgrammeDDD.class);
        ProgrammeDDD mockProgramme2 = mock(ProgrammeDDD.class);

        List<ProgrammeDDD> originalList = new ArrayList<>();
        originalList.add(mockProgramme1);
        originalList.add(mockProgramme2);

        // act
        List<ProgrammeDDD> copiedList = factory.copyProgrammeArrayList(originalList);

        // assert
        assertNotSame(originalList, copiedList);
    }
}