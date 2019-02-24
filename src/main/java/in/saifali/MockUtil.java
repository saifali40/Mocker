package in.saifali;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;

/**
 * @author saif
 * @version 0.01
 */
public class MockUtil extends MockUtilActions {

    private MockUtil() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * @param t Class
     * @param kv Key Value pair from user
     * @param skip Fields to Skip
     * @param <T> Template Class
     * @return the classObject
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

    /**
     *
     * @param t Class
     * @param stringkv Key Value pair from user
     * @param skipList Fields to Skip
     * @param <T> Template Class
     * @return the classObject
     */
    public static <T> T getMockData(Class<T> t, String stringkv, String skipList) {
        Map<String, Object> kv = gson.fromJson(stringkv, typeMap);
        List<String> skip = gson.fromJson(skipList, typeList);
        return getMockData(t, kv, skip);
    }

    /**
     *
     * @param t Class
     * @param kv Key Value pair from user
     * @param <T> Template Class
     * @return the classObject
     */
    public static <T> T getMockData(Class<T> t, Map<String, Object> kv) {
        return getMockData(t, kv, null);
    }


    /**
     *
     * @param t Class
     * @param skip Fields to Skip
     * @param <T> the template Class
     * @return the classObject
     */
    public static <T> T getMockData(Class<T> t, List<String> skip) {
        return getMockData(t, null, skip);
    }

    /**
     *
     * @param t class
     * @param <T> template Class
     * @return the classObject
     */
    public static <T> T getMockData(Class<T> t) {
        return getMockData(t, "", "");
    }

    /**
     *
     * @param t class
     * @param stringKv array of fields to skip
     * @param <T> template Class
     * @return the ClassObject
     */
    public static <T> T getMockData(Class<T> t, String... stringKv) {
        return getMockData(t, null, Arrays.asList(stringKv));
    }

}
