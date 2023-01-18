package com.check.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 服务网关token生成
 * @author zzc
 */
@Slf4j
public class GzcssTokenUtils {

    /**
     * 生成签名token
     * @param appId 系统id
     * @param secret 系统密钥
     * @return 签名token
     */
    public static String getToken(String appId,String secret,Long timestamp,String nonce) {
        Map<String, Object> payload = new LinkedHashMap<>();
        //系统id
        payload.put("appId", appId);
        //签注时间
        payload.put("timestamp", timestamp);
        //随机数
        payload.put("nonce", nonce);
        //MD5加密明文密钥
        payload.put("secret",secret);
        //顺序不可调换,JSON串的格式实例
        //{"appId":"9999","timestamp":1626934559872,"nonce":"a71b6c61-af07-458b-afce-15bfcec280ff","secret":"8b0075e9f9b80321913441a26ee199be"}
        return sM3Encode(JSON.toJSONString(payload));
    }

    public static String sM3Encode(String str) {
        byte[] md = new byte[32];
        byte[] msg1 = str.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        byte[] encode = Hex.encode(md);
        return new String(encode);
    }

    /**
     * MD5加密，
     * Spring 环境可用 DigestUtils.md5DigestAsHex(secret.getBytes())代替
     * @param s s
     * @return String
     */
    public static String mD5Encode(String s)  {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte[] bytes = md.digest();
            byte[] encode = Hex.encode(bytes);
            return new String(encode);
        }catch (Exception e){
            return "";
        }
    }

    public static boolean isValid(String appId, Long timestamp, String nonce, String md5Secret, String signature){
        Map<String, Object> payload = new LinkedHashMap<>();
        //系统id
        payload.put("appId", appId);
        //签注时间
        payload.put("timestamp", timestamp);
        //随机数
        payload.put("nonce", nonce);
        payload.put("secret",md5Secret);
        return StringUtils.equals(sM3Encode(JSON.toJSONString(payload)),signature);
    }
}
