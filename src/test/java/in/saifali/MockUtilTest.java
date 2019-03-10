package in.saifali;

import com.google.gson.Gson;
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
        assertNotEquals("", MockUtil.randomString.get());
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
        TestModel model = MockUtil.getMockData(TestModel.class, "", "[string]");
        System.out.println(new Gson().toJson(model));
        assertNotNull(model);
    }

    @Test
    public void getMockData_withoutNoAdditionalParam() {
        assertNotNull(MockUtil.getMockData(TestModel.class));
    }

    @Test
    public void getMockData_withMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("string","noString");
        assertNotNull(MockUtil.getMockData(TestModel.class,map));
    }

    @Test
    public void  getMockDataWithAll(){
        assertNotNull(MockUtil.getMockData(TestModel.class, "{string:string}", "[nodate]"));
    }

    @Test
    public void getMock_withList(){
        List<String> list = new ArrayList<>();
        list.add("nodate");
        assertNotNull(MockUtil.getMockData(TestModel.class, list));
    }
}
