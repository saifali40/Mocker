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
public class MockUtilTest {
    @Test
    public void getStringCheck() {
        assertNotEquals("getString", MockUtil.getString());
    }

    @Test
    public void getMockData_withoutKvandList() {
        assertNotNull(MockUtil.getMockData(TestModel.class, "", ""));
    }

    @Test
    public void getMockData_withKv() {
        assertNotNull(MockUtil.getMockData(TestModel.class, "{string:string}", ""));
    }

    @Test
    public void getMockData_withList() {
        assertNotNull(MockUtil.getMockData(TestModel.class, "", "[string]"));
    }
}
