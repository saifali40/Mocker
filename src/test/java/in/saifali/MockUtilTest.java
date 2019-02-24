package in.saifali;

import in.saifali.mockmodels.TestModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class MockUtilTest{
    @Test
    public void getStringCheck(){
        assertNotEquals("getString",MockUtil.getString());
    }
    @Test
    public void testObject(){
        assertNotNull(MockUtil.getMockData(TestModel.class,null,null));
    }
}
