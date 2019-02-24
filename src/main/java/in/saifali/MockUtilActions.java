package in.saifali;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MockUtilActions {

    static Logger log = Logger.getLogger(MockUtil.class.getName());
    static Gson gson = new Gson();

    static Class<? extends Map> typeMap = Map.class;
    static Class<? extends List> typeList = List.class;

    protected static void setUserValue(Map<String, Object> kv, Map<String, Object> map) {
        if (kv != null)
            map.putAll(kv);
    }

    protected static <T> void getFieldsByType(Class<T> t, Map<String, Object> map, T clz, Field x) {
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

    protected static <T> void getDefaultValues(Class<T> t, Map<String, Object> map, T clz, Field x) throws NoSuchFieldException, IllegalAccessException {
        Field field = t.getDeclaredField(x.getName());
        field.setAccessible(true);
        Object value = field.get(clz);
        if (value != null)
            map.put(x.getName(), value);
    }

    protected static <T> List<Field> getFields(Class<T> t, List<String> skip) {
        List<Field> fields = Arrays.asList(t.getDeclaredFields());

        if (skip != null) {
            fields = fields.stream()
                    .filter(x -> !skip.contains(x.getName()))
                    .collect(Collectors.toList());
        }
        return fields;
    }

    protected static List<String> allowedType() {
        String[] allowed = {"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Long",
                "boolean"};
        return Arrays.asList(allowed);
    }

    protected static String getString() {
        return UUID.randomUUID().toString();
    }

    protected static int getInteger() {
        return new Random().nextInt((1000) + 1) + 100000;
    }

    protected static long getLong() {
        return new Random().nextLong() + 100000L;
    }

    protected static boolean getBoolean() {
        return System.nanoTime() % 2 != 0;
    }
}
