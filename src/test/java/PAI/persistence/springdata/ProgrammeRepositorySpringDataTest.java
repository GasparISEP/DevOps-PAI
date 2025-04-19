package PAI.persistence.springdata;

import PAI.domain.programme.Programme;
import PAI.mapper.IProgrammeMapper;
import PAI.persistence.datamodel.ProgrammeDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeRepositorySpringDataTest {

    @Test
    void shouldCreateProgrammeRepoSpringData() {
        //arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);

        //act
        ProgrammeRepositorySpringData result = new ProgrammeRepositorySpringData(iProgMapper,iProgRepo);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldNotCreateProgrammeRepoSpringDataWhenIProgRepoSpringDataIsNull() {
        //arrange
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeRepositorySpringData(iProgMapper,null);
        });
    }

    @Test
    void shouldNotCreateProgrammeRepoSpringDataWhenIProgMapperIsNull() {
        //arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeRepositorySpringData(null,iProgRepo);
        });
    }

    @Test
    void shouldSaveProgramme() throws Exception {
        //arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);

        Programme prog = mock(Programme.class);
        ProgrammeDataModel progDM = mock(ProgrammeDataModel.class);

        ProgrammeRepositorySpringData progRepo = new ProgrammeRepositorySpringData(iProgMapper, iProgRepo);

        //act
        when(iProgMapper.toData(prog)).thenReturn(progDM);
        when(iProgMapper.toDomain(progDM)).thenReturn(prog);

        //assert
        assertNotNull(progRepo.save(prog));
    }

    @Test
    void shouldNotSaveProgrammeWhenProgIsNull() throws Exception {
        //arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);

        ProgrammeRepositorySpringData progRepo = new ProgrammeRepositorySpringData(iProgMapper, iProgRepo);

        //act + assert
        assertNull(progRepo.save(null));
    }
}