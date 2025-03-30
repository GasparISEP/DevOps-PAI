package PAI.repository.accessMethodRepositoryDDD;

import PAI.domain.accessMethodDDD.AccessMethodDDDFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccessMethodDDDRepositoryTest {

    @Test
    void shouldCreateAccessMethodRepository(){
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactory = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactory = mock(AccessMethodDDDListFactoryImpl.class);
        //act
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository(doubleAccessMethodFactory, doubleAccessMethodListFactory);
        //assert
        assertNotNull(accessMethodRepository);
    }

}