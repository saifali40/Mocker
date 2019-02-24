package in.saifali;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;

/**
 * @author saif
 * @version 0.01
 * @since
 */
public class MockUtil extends MockUtilActions {

    private MockUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param {class}        t
     * @param {Map<String,   Object>} kv
     * @param {List<String>} skip
     * @param <T>
     * @return the <T>
     */


    public static <T> T getMockData(Class<T> t, Map<String, Object> kv, List<String> skip) {
        Map<String, Object> map = new HashMap<>();
        List<Field> fields = getFields(t, skip);
        try {
            T clz = t.newInstance();
            fields.stream()
                    .forEach(x -> {
                        if (allowedType().contains(x.getType().getName())) {
                            getFieldsByType(t, map, clz, x);
                        }
                    });
            setUserValue(kv, map);
            String json = gson.toJson(map);
            return gson.fromJson(json, t);
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
            throw new IllegalArgumentException();
        }
    }

    public static <T> T getMockData(Class<T> t, String stringKv, String skipList) {
        Map<String, Object> kv = gson.fromJson(stringKv, typeMap);
        List<String> skip = gson.fromJson(skipList, typeList);
        return getMockData(t, kv, skip);
    }

    public static <T> T getMockData(Class<T> t, Map<String, Object> kv) {
        return getMockData(t, kv, null);
    }

    public static <T> T getMockData(Class<T> t, List<String> skip) {
        return getMockData(t, null, skip);
    }

    public static <T> T getMockData(Class<T> t) {
        return getMockData(t, "", "");
    }

    public static <T> T getMockData(Class<T> t, String... stringKv) {
        return getMockData(t, null, Arrays.asList(stringKv));
    }

}
