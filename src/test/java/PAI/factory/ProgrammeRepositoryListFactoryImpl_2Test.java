package PAI.factory;


import PAI.domain.Programme_2;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeRepositoryListFactoryImpl_2Test {
    @Test
    void shouldCreateRepositoryListFactoryImpl(){
        ProgrammeRepositoryListFactoryImpl_2 programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl_2();
        //act
        List<Programme_2> programmeList = programmeRepositoryListFactory.newProgrammeArrayList();
        //assert
        assertNotNull(programmeList);
        assertInstanceOf(ArrayList.class, programmeList);
    }

    @Test
    void whenCopyProgrammeArrayListInvoked_ThenShouldReturnNewIndependentCopy() {
        // arrange
        ProgrammeRepositoryListFactoryImpl_2 factory = new ProgrammeRepositoryListFactoryImpl_2();

        Programme_2 mockProgramme1 = mock(Programme_2.class);
        Programme_2 mockProgramme2 = mock(Programme_2.class);

        List<Programme_2> originalList = new ArrayList<>();
        originalList.add(mockProgramme1);
        originalList.add(mockProgramme2);

        // act
        List<Programme_2> copiedList = factory.copyProgrammeArrayList(originalList);

        // assert
        assertNotSame(originalList, copiedList);
    }
}