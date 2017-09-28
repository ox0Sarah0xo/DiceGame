package JUnit;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Sarah Fromming on 27/09/2017.
 */
public class TestJUnit {
    @Test
    public void testAdd() {
        //test data
        int num = 5;
        String temp = null;
        String str = "Junit is working fine";

        //check for equality
        assertEquals("Junit is working fine", str);

        //check for false condition
        assertFalse(num > 6);

        //check for not null value
        assertNotNull(str);
    }
}
