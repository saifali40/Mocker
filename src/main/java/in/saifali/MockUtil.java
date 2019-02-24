package in.saifali;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MockUtil {
    static Logger log = Logger.getLogger(MockUtil.class.getName());
    Gson gson = new Gson();

    public <T> T getMockData(Class<T> t, Map<String, Object> kv, List<String> skip) {
        Map<String, Object> map = new HashMap<>();
        List<Field> fields = getFields(t, skip);
        try {
            T clz = t.newInstance();
            fields.stream()
                    .forEach(x -> {
                        if (allowedType().contains(x.getType().getName())) {
                            x.setAccessible(true);
                            if (x.getType().getName().toLowerCase().contains("boolean")) {
                                map.put(x.getName(), getBoolean());
                            }
                            if (x.getType().getName().toLowerCase().contains("string")) {
                                map.put(x.getName(), getString());
                                try {
                                    Field field = t.getDeclaredField(x.getName());
                                    field.setAccessible(true);
                                    String value = (String) field.get(clz);
                                    map.put(x.getName(),value);
                                }catch (Exception e){
                                    log.log(Level.WARNING, e.toString());
                                }
                            }
                            if (x.getType().getName().toLowerCase().contains("integer")) {
                                map.put(x.getName(), getInteger());
                            }
                            if (x.getType().getName().toLowerCase().contains("long")) {
                                map.put(x.getName(), getLong());
                            }
                        }
                    });
            map.putAll(kv);
            String json = gson.toJson(map);
            return gson.fromJson(json, t);
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
            throw new IllegalArgumentException();
        }
    }

    private <T> List<Field> getFields(Class<T> t, List<String> skip) {
        List<Field> fields = Arrays.asList(t.getDeclaredFields());

        if (skip != null) {
            fields = fields.stream()
                    .filter(x -> !skip.contains(x.getName()))
                    .collect(Collectors.toList());
        }
        return fields;
    }

    public List<String> allowedType() {
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
