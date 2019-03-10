package in.saifali.MockData;

import com.google.gson.Gson;
import in.saifali.MockUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class MockUtilActions {


    protected MockUtilActions(){
        throw new IllegalStateException("Utility class");
    }

    protected static Logger log = Logger.getLogger(MockUtil.class.getName());

    protected static Class<? extends Map> typeMap = Map.class;
    protected static Class<? extends List> typeList = List.class;

    protected static Gson gson = new Gson();

    protected static void setUserValue(Map<String, Object> kv, Map<String, Object> map) {
        if (kv != null)
            map.putAll(kv);
    }

    protected static <T> void getFieldsByType(Class<T> t, Map<String, Object> map, T clz, Field x) {
        x.setAccessible(true);
        if (x.getType().getName().toLowerCase().contains("boolean")) {
            map.put(x.getName(), randomBool.getAsBoolean());
        }
        if (x.getType().getName().toLowerCase().contains("string")) {
            map.put(x.getName(), randomString.get());
        }
        if (x.getType().getName().toLowerCase().contains("integer")) {
            map.put(x.getName(), randomInt.getAsInt());
        }
        if (x.getType().getName().toLowerCase().contains("long")) {
            map.put(x.getName(), randomLong.getAsLong());
        }

        if(x.getGenericType().getTypeName().contains("Set")){
            ParameterizedType type = (ParameterizedType) x.getGenericType();
            Type key = type.getActualTypeArguments()[0];
            map.put(x.getName(), randomSet(key));
        }
        if(x.getGenericType().getTypeName().contains("Map")){
            ParameterizedType type = (ParameterizedType) x.getGenericType();
            Type key = type.getActualTypeArguments()[0];
            Type value = type.getActualTypeArguments()[1];
            map.put(x.getName(), randomMap(key,value));
        }
        if(x.getGenericType().getTypeName().contains("List")){
            ParameterizedType type = (ParameterizedType) x.getGenericType();
            Type key = type.getActualTypeArguments()[0];
            map.put(x.getName(), randomList(key));
        }
        getDefaultValue(t, map, clz, x);
    }

    private static <T> void getDefaultValue(Class<T> t, Map<String, Object> map, T clz, Field x) {
        try {
            getDefaultValues(t, map, clz, x);
        } catch (Exception e) {
            log.log(Level.WARNING, e.toString());
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


    public static final  Supplier<String> randomString = () -> UUID.randomUUID().toString();

    protected static IntSupplier randomInt = () -> new Random().nextInt((1000) + 1) + 100000;

    protected static LongSupplier randomLong = () -> new Random().nextLong() + 100000L;

    protected static BooleanSupplier randomBool = () -> System.nanoTime() % 2 != 0;

    protected static Supplier<Date> randomDate = () -> new Date(System.currentTimeMillis());

    protected static Set randomSet(Type key){
        Set retVal = new HashSet<>();
        if(key.getTypeName().toLowerCase().contains("boolean"))
            retVal.add(randomBool.getAsBoolean());

        if(key.getTypeName().toLowerCase().contains("string"))
            retVal.add(randomString.get());

        if(key.getTypeName().toLowerCase().contains("integer"))
            retVal.add(randomInt.getAsInt());

        if(key.getTypeName().toLowerCase().contains("long"))
            retVal.add(randomLong.getAsLong());

        return retVal;
    };

    protected static List randomList(Type key){
        List retVal = new ArrayList();
        if(key.getTypeName().toLowerCase().contains("boolean"))
            retVal.add(randomBool.getAsBoolean());

        if(key.getTypeName().toLowerCase().contains("string"))
            retVal.add(randomString.get());

        if(key.getTypeName().toLowerCase().contains("integer"))
            retVal.add(randomInt.getAsInt());

        if(key.getTypeName().toLowerCase().contains("long"))
            retVal.add(randomLong.getAsLong());

        return retVal;
    };

    private static Object randomMap(Type key, Type value) {
        Map retVal = new HashMap();

        Object index = null;

        if(key.getTypeName().toLowerCase().contains("boolean"))
            index = randomBool.getAsBoolean();

        if(key.getTypeName().toLowerCase().contains("string"))
            index = randomString.get();

        if(key.getTypeName().toLowerCase().contains("integer"))
            index = randomInt.getAsInt();

        if(key.getTypeName().toLowerCase().contains("long"))
            index = randomLong.getAsLong();



        if(value.getTypeName().toLowerCase().contains("boolean"))
            retVal.put(index, randomBool.getAsBoolean());

        if(value.getTypeName().toLowerCase().contains("string"))
            retVal.put(index,randomString.get());

        if(value.getTypeName().toLowerCase().contains("integer"))
            retVal.put(index,randomInt.getAsInt());

        if(value.getTypeName().toLowerCase().contains("long"))
            retVal.put(index,randomLong.getAsLong());


        return retVal;
    };


}
