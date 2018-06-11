package com.imooc.util;

import java.util.Random;

/**
 * Created by: xmg
 * 2018/3/28 15:34
 */

public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public synchronized static String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
