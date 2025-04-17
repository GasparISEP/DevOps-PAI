package PAI.repository.accessMethodRepository;

import PAI.domain.accessMethod.AccessMethod;
import PAI.persistence.mem.accessMethod.AccessMethodListFactoryImpl;
import PAI.persistence.mem.accessMethod.IAccessMethodListFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodListFactoryImplTest {

    @Test
    void shouldCreateAccessMethodListFactory(){
        //arrange
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        //act
        List<AccessMethod> accessMethods = accessMethodListFactory.createAccessMethodList();
        //assert
        assertNotNull(accessMethodListFactory);
        assertNotNull(accessMethods);
        assertInstanceOf(ArrayList.class,accessMethods);
    }

}