package com.imooc.util;

/**
 * Created by: xmg
 * 2018/3/30 19:55
 */

public class MathUtil {

    public static final Double MONEY_RANGE = 0.01;

    /**
     * 比较两个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        // if (result<MONEY_RANGE){
        //     return true;
        // }else {
        //     return false;
        // }

        return result<MONEY_RANGE;
    }
}
