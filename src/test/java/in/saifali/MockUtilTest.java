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

@RunWith(JUnit4.class)
public class MockUtilTest extends MockUtil {

    @Test
    public void getStringCheck(){
        assertNotEquals("getString",getString());
    }

    @Test
    public void testObject(){
        Map<String, Object> kv = new HashMap<>();
        kv.put("stringwithValue","ABCD");
        List<String> skip = new ArrayList<>();
        getMockData(TestModel.class,kv,skip);
    }

}
