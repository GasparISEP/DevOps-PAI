package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccessMethodTest {

    @Test
    void shouldCreateAccessMethodOnlyWithValidName(){
        //arrange
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //act
        AccessMethod accessMethod = new AccessMethod(accessMethodName);
        //assert
        assertNotNull(accessMethod);
    }

    @Test
    void shouldThrowExceptionWithNullName(){
        //arrange
        //act + assert
        assertThrows(Exception.class, () -> new AccessMethod(null));
    }

    @Test
    void shouldCreateAccessMethodWithValidName(){
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //act
        AccessMethod accessMethod = new AccessMethod(accessMethodId, accessMethodName);
        //assert
        assertNotNull(accessMethod);
    }

    @Test
    void shouldThrowExceptionIfNullIdentifier(){
        //arrange
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //act
        //assert
        assertThrows(Exception.class, () -> new AccessMethod(null, accessMethodName));
    }

    @Test
    void shouldThrowExceptionIfNullName(){
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        //act
        //assert
        assertThrows(Exception.class, () -> new AccessMethod(accessMethodId, null));
    }

    @Test
    void shouldReturnAccessMethodIdentity() {
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod = new AccessMethod(accessMethodId, accessMethodName);
        //act
        AccessMethodID accessMethodID = accessMethod.identity();
        //assert
        assertNotNull(accessMethodID);
    }

    @Test
    void shouldReturnTrueIfAccessMethodHasSameNameAsOther() {
        //arrange
        AccessMethodID accessMethodId1 = mock(AccessMethodID.class);
        AccessMethodID accessMethodId2 = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod1 = new AccessMethod(accessMethodId1,accessMethodName);
        AccessMethod accessMethod2 = new AccessMethod(accessMethodId2, accessMethodName);
        //act
        boolean result = accessMethod1.sameAs(accessMethod2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodHasDifferentNameAsOther() {
        //arrange
        AccessMethodID accessMethodId1 = mock(AccessMethodID.class);
        AccessMethodID accessMethodId2 = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars accessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod1 = new AccessMethod(accessMethodId1,accessMethodName1);
        AccessMethod accessMethod2 = new AccessMethod(accessMethodId2, accessMethodName2);
        //act
        boolean result = accessMethod1.sameAs(accessMethod2);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfComparedWithOtherObject(){
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod = new AccessMethod(accessMethodId, accessMethodName);
        String name = "AccessMethod";
        //act
        boolean result = accessMethod.sameAs(accessMethodId);
        boolean result2 = accessMethod.equals(name);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void shouldReturnTrueIfEqualsComparesSameObject(){
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod = new AccessMethod(accessMethodId, accessMethodName);
        //act
        boolean result = accessMethod.equals(accessMethod);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfEqualsComparesObjectsWithSameAccessMethodId(){
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars accessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod1 = new AccessMethod(accessMethodId, accessMethodName1);
        AccessMethod accessMethod2 = new AccessMethod(accessMethodId, accessMethodName2);
        //act
        boolean result = accessMethod1.equals(accessMethod2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfGivenNameEqualsAccessMethodNameIsolationTest(){
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod = new AccessMethod(accessMethodId, accessMethodName);
        //act
        boolean result = accessMethod.hasThisAccessMethodName(accessMethodName);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfGivenNameEqualsAccessMethodName(){
        //arrange
        String maiores23 = "M23";
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = new NameWithNumbersAndSpecialChars(maiores23);
        NameWithNumbersAndSpecialChars accessMethodNameToSearch = new NameWithNumbersAndSpecialChars(maiores23);
        AccessMethod accessMethod = new AccessMethod(accessMethodId, accessMethodName);
        //act
        boolean result = accessMethod.hasThisAccessMethodName(accessMethodNameToSearch);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfGivenNameDoesNotEqualsAccessMethodNameIsolationTest(){
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars accessMethodNameToSearch = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod = new AccessMethod(accessMethodId, accessMethodName);
        //act
        boolean result = accessMethod.sameAs(accessMethodNameToSearch);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnCorrectAccessMethodID() {
        //arrange
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        AccessMethodID accessMethodIDToSearch = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod = new AccessMethod(accessMethodId, accessMethodName);
        //act
        boolean result = accessMethod.identity().equals(accessMethodIDToSearch);
        //assert
        assertFalse(result);
    }
}