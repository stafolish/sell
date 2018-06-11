package com.imooc.util;

import com.imooc.enums.CodeEnum;

/**
 * Created by: xmg
 * 2018/3/31 12:30
 */

public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode()))
                return each;
        }
        return null;
    }
}
