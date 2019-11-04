package fr.adlere.bnp.supermarket.checkout;

import fr.adlere.bnp.supermarket.checkout.util.ItemUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemUtilsTest {

    @Test
    void testRound_WhenSecondArgumentIsNegative_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ItemUtils.round(2.568, -1));
    }

    @Test
    void testRound_WhenValueIs5_24589_AndNIsEquelTo2_ThenReturn_5_24() {
        assertEquals(5.25, ItemUtils.round(5.24589, 2));
    }

    @Test
    void testRound_WhenValueIs5_24589_AndNIsEquelTo2_ThenReturn_5_0() {
        assertEquals(5.0, ItemUtils.round(5.24589, 0));
    }
}