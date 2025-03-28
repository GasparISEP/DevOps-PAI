package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.AccessMethod;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodDDDTest {

    @Test
    void shouldCreateAccessMethodWithValidName(){
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //act
        AccessMethodDDD accessMethod = new AccessMethodDDD(accessMethodId, accessMethodName);
        //assert
        assertNotNull(accessMethod);
    }

    @Test
    void shouldThrowExceptionIfNullIdentifier(){
        //arrange
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //act
        //assert
        assertThrows(Exception.class, () -> new AccessMethodDDD(null, accessMethodName));
    }

    @Test
    void shouldThrowExceptionIfNullName(){
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        //act
        //assert
        assertThrows(Exception.class, () -> new AccessMethodDDD(accessMethodId, null));
    }

    @Test
    void shouldReturnAccessMethodIdentity() {
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodDDD accessMethod = new AccessMethodDDD(accessMethodId, accessMethodName);
        //act
        AccessMethodID accessMethodID = accessMethod.identity();
        //assert
        assertNotNull(accessMethodID);
    }

    @Test
    void sameAs() {
    }
}