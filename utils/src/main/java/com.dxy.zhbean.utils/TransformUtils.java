package com.dxy.zhbean.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/5
 */
public class TransformUtils {


    /**
     * java bean to map
     *
     * @param objcet
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object objcet) throws IllegalAccessException {
        if (null == objcet) {
            return null;
        }
        /** 获取关联的所有类，本类及所有父类 */
        boolean flag = true;
        Class clazz = objcet.getClass();
        List<Class> clazzs = new ArrayList<>();
        while (flag) {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
            if (null == clazz || clazz == Object.class) {
                break;
            }
        }

        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < clazzs.size(); i++) {
            Field[] declaredFields = clazzs.get(i).getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                //过滤static和final类型
                if (Modifier.isFinal(mod) || Modifier.isStatic(mod)) {
                    continue;
                }
                field.setAccessible(true);
                map.put(field.getName(), field.get(objcet));
            }
        }
        return map;
    }

    /**
     * map to object
     *
     * @param map
     * @param beanClass
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws IllegalAccessException, InstantiationException {
        if (null == map || map.size() <= 0) {
            return null;
        }

        Object obj = beanClass.newInstance();
        //获取关联的所有类（本类和所有父类）
        boolean flag = false;
        Class clazz = obj.getClass();
        List<Class> clazzes = new ArrayList<>();
        while (flag) {
            clazzes.add(clazz);
            clazz = clazz.getSuperclass();
            if (null == clazz || clazz == Object.class) {
                break;
            }
        }

        for (int i = 0; i < clazzes.size(); i++) {
            Field[] declaredFields = clazzes.get(i).getDeclaredFields();
            for (Field field : declaredFields) {
                //过滤final和static
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                //由字符串转回对象对应的类型
                if (field != null) {
                    field.setAccessible(true);
                    field.set(obj, map.get(field.getName()));
                }
            }
        }
        return obj;
    }

    /**
     * json to map
     * complex json -> map nest map
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> jsonString2Map(String jsonString) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }

        //最外层解析
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        for (Object k : jsonObject.keySet()) {
            Object v = jsonObject.get(k);

            // 内层如果还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<>();
                Iterator<Object> iterator = ((JSONArray) v).iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    //递归
                    list.add(jsonString2Map(next.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }

        return map;
    }


}
