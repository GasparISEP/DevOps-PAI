package PAI.repository.accessMethodRepositoryDDD;

import PAI.domain.accessMethodDDD.AccessMethodDDD;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodDDDListFactoryImplTest {

    @Test
    void shouldCreateAccessMethodListFactory(){
        //arrange
        IAccessMethodDDDListFactory accessMethodListFactory = new AccessMethodDDDListFactoryImpl();
        //act
        List<AccessMethodDDD> accessMethods = accessMethodListFactory.createAccessMethodList();
        //assert
        assertNotNull(accessMethodListFactory);
        assertNotNull(accessMethods);
        assertInstanceOf(ArrayList.class,accessMethods);
    }

}