package PAI.factory;

import PAI.domain.Programme;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeRepositoryListFactoryImplTest {

    @Test
    void whenCopyProgrammeArrayListInvoked_ThenShouldReturnNewIndependentCopy() {
        // arrange
        ProgrammeRepositoryListFactoryImpl factory = new ProgrammeRepositoryListFactoryImpl();

        Programme mockProgramme1 = mock(Programme.class);
        Programme mockProgramme2 = mock(Programme.class);

        List<Programme> originalList = new ArrayList<>();
        originalList.add(mockProgramme1);
        originalList.add(mockProgramme2);

        // act
        List<Programme> copiedList = factory.copyProgrammeArrayList(originalList);

        // assert
        assertNotSame(originalList, copiedList);
    }

}
