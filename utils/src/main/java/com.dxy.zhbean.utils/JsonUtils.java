package com.dxy.zhbean.utils;

import org.noggit.JSONParser;
import org.noggit.ObjectBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * 1.将json字符串转为map
 * 2.获取map指定节点的value
 *
 * @author: zhbean
 * @Date: 2018/9/5
 */
public class JsonUtils {


    /**
     * 将json字符串转为List
     * @param json
     * @return
     * @throws IOException
     */
    public static List<Object> json2List(String json) throws IOException {
        return transJson(List.class, json);
    }

    /**
     * 将json字符串转为map
     * @param json
     * @return
     * @throws IOException
     */
    public static Map<String, Object> json2Map(String json) throws IOException {
        return transJson(Map.class, json);
    }

    /**
     * transform json string to the special class
     * @param clazz
     * @param json
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T transJson(Class<T> clazz, String json) throws IOException {
        T result = null;
        Object val = ObjectBuilder.getVal(new JSONParser(json));
        if (clazz.isAssignableFrom(val.getClass())) {
            result = (T) val;
        } else {
            throw new IllegalStateException("Expected a " + clazz.getName() + " but found " + val + " instead! " + json);
        }
        return result;
    }


    /**
     * read a String value from a json object tree
     *
     * @param jsonPath
     * @param json
     * @return
     */
    public static String asString(String jsonPath, Map<String, Object> json) {
        return pathAs(String.class, jsonPath, json);
    }

    /**
     * read a Long value from a json object tree
     *
     * @param jsonPath
     * @param json
     * @return
     */
    public static Long asLong(String jsonPath, Map<String, Object> json) {
        return pathAs(Long.class, jsonPath, json);
    }

    /**
     * read a List from a json object tree
     *
     * @param jsonPath
     * @param json
     * @return
     */
    public static List<String> asList(String jsonPath, Map<String, Object> json) {
        return pathAs(List.class, jsonPath, json);
    }

    /**
     * read a Map from a json object tree
     *
     * @param jsonPath
     * @param json
     * @return
     */
    public static Map<String, Object> asMap(String jsonPath, Map<String, Object> json) {
        return pathAs(Map.class, jsonPath, json);
    }


    public static <T> T pathAs(Class<T> clazz, String jsonPath, Map<String, Object> json) {
        T result = null;
        Object obj = atPath(jsonPath, json);

        if (obj != null) {
            if (clazz.isAssignableFrom(obj.getClass())) {
                result = (T) obj;
            } else {
                throw new IllegalStateException("Expected a " + clazz.getName() + " at path " +
                        jsonPath + " but found " + obj + " instead! " + json);
            }
        }
        return result;
    }


    /**
     * read an object of unknow type from a json object tree
     *
     * @param jsonPath
     * @param json
     * @return
     */
    public static Object atPath(String jsonPath, Map<String, Object> json) {
        if ("/".equals(jsonPath)) {
            return json;
        }

        if (!jsonPath.startsWith("/")) {
            throw new IllegalArgumentException("Invalid JSON path: " + jsonPath + "! Must start with a /");
        }

        Map<String, Object> parent = json;
        Object result = null;
        /**  Break on all slashes _not_ preceeded by a backslash */
        String[] path = jsonPath.split("(?<![\\\\])/");
        for (int p = 1; p < path.length; p++) {
            String part = path[p];

            if (part.startsWith("\\")) {
                part = part.substring(1);
            }

            Object child = parent.get(part);
            if (child == null)
                break;

            if (p == path.length - 1) {
                // success - found the node at the desired path
                result = child;
            } else {
                if (child instanceof Map) {
                    // keep walking the path down to the desired node
                    parent = (Map) child;
                } else {
                    // early termination - hit a leaf before the requested node
                    break;
                }
            }
        }
        return result;
    }

}
