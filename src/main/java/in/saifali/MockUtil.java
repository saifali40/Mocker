package in.saifali;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MockUtil {
    static Logger log = Logger.getLogger(MockUtil.class.getName());
    static Gson gson = new Gson();

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

            if(kv!=null)
                map.putAll(kv);

            String json = gson.toJson(map);
            return gson.fromJson(json, t);
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
            throw new IllegalArgumentException();
        }
    }

    private static <T> void getFieldsByType(Class<T> t, Map<String, Object> map, T clz, Field x) {
        x.setAccessible(true);
        if (x.getType().getName().toLowerCase().contains("boolean")) {
            map.put(x.getName(), getBoolean());
        }
        if (x.getType().getName().toLowerCase().contains("string")) {
            map.put(x.getName(), getString());
            try {
                getDefaultValues(t, map, clz, x);
            } catch (Exception e) {
                log.log(Level.WARNING, e.toString());
            }
        }
        if (x.getType().getName().toLowerCase().contains("integer")) {
            map.put(x.getName(), getInteger());
            try {
                getDefaultValues(t, map, clz, x);
            } catch (Exception e) {
                log.log(Level.WARNING, e.toString());
            }
        }
        if (x.getType().getName().toLowerCase().contains("long")) {
            map.put(x.getName(), getLong());
            try {
                getDefaultValues(t, map, clz, x);
            } catch (Exception e) {
                log.log(Level.WARNING, e.toString());
            }
        }
    }

    private static <T> void getDefaultValues(Class<T> t, Map<String, Object> map, T clz, Field x) throws NoSuchFieldException, IllegalAccessException {
        Field field = t.getDeclaredField(x.getName());
        field.setAccessible(true);
        Object value = field.get(clz);
        if(value!=null)
            map.put(x.getName(), value);
    }

    private static <T> List<Field> getFields(Class<T> t, List<String> skip) {
        List<Field> fields = Arrays.asList(t.getDeclaredFields());

        if (skip != null) {
            fields = fields.stream()
                    .filter(x -> !skip.contains(x.getName()))
                    .collect(Collectors.toList());
        }
        return fields;
    }

    public static List<String> allowedType() {
        String[] allowed = {"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Long",
                "boolean"};
        return Arrays.asList(allowed);
    }

    public static String getString() {
        return UUID.randomUUID().toString();
    }

    public static int getInteger() {
        return new Random().nextInt((1000) + 1) + 100000;
    }

    public static long getLong() {
        return new Random().nextLong() + 100000L;
    }

    public static boolean getBoolean() {
        return System.nanoTime() % 2 != 0;
    }

}
