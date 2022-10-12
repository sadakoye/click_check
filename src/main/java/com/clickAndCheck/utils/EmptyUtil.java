package com.clickAndCheck.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zzc
 */
public class EmptyUtil {

    public static boolean isNotEmpty(List<?> list) {
        AtomicBoolean b = new AtomicBoolean(false);
        if (list != null){
            list.forEach(item -> {
                if (item != null){
                    b.set(true);
                }
            });
        }
        return b.get();
    }

    public static boolean isNotEmpty(Object o) {
        AtomicBoolean b = new AtomicBoolean(false);
        if (o != null){
            if (o instanceof String){
                String s = (String) o;
                if (StringUtils.isNotBlank(s)){
                    b.set(true);
                }
            }else {
                b.set(true);
            }
        }
        return b.get();
    }
}
