package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MaxEctsTest {

    @Test
    void shouldStoreMaxEctsValueCorrectly() {
        int expectedValue = 240 ;
        MaxEcts maxEcts = new MaxEcts(expectedValue);

        assertEquals(expectedValue, maxEcts.getMaxEcts(), "O valor do maxEcts não foi armazenado corretamente.");
    }
  
}