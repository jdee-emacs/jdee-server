import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CorrectAnnotatedClass {

    @Test
    public void mathShouldStillWork() {
        assertEquals(4, 2+2);
    }
}
