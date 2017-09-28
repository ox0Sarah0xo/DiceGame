package JUnit;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Sarah Fromming on 27/09/2017.
 */
public class JUnitTest {
    @Test
    public void testBalanceWhenWinning(){
        //test data
        int balance = 50;
        int matches = 1;
        int bet = 5;
        int winnings = (matches * bet) * 2;

        //check for equality
        assertEquals(10, winnings);

        //check for false condition
        assertFalse(balance > 200);
    }
}
