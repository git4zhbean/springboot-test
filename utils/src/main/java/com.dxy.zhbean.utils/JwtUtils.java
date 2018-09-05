package com.dxy.zhbean.utils;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Token 加/解密工具
 * @author: zhbean
 * @Date: 2018/9/5
 */
public class JwtUtils {

    /**
     * 密钥
     */
    private static final String SECRET = "search";
    /**
     * 默认字段key： exp
     */
    private static final String EXP = "exp";
    /**
     * 默认字段key： payload
     */
    private static final String PAYLOAD = "payload";


    /**
     * 加密
     *
     * @param object
     * @param maxTime
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> String encode(T object, long maxTime) {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);

            final Map<String, Object> data = new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(object);
            data.put(PAYLOAD, jsonString);
            data.put(EXP, System.currentTimeMillis() + maxTime);

            return signer.sign(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 数据解密
     *
     * @param jwt
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T decode(String jwt, Class<T> tClass) {
        final JWTVerifier jwtVerifier = new JWTVerifier(SECRET);

        try {
            final Map<String, Object> data = jwtVerifier.verify(jwt);
            //判断数据是否超时或者符合标准
            if (data.containsKey(EXP) && data.containsKey(PAYLOAD)) {
                long exp = (long) data.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String) data.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, tClass);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





}
