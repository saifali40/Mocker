package in.saifali;

import org.junit.Test;

public class MockUtilActionsTest {

    @Test(expected = IllegalStateException.class)
    public void testConstructor(){
        new MockUtilActions();
    }

}
