package com.imooc.enums;

import lombok.Getter;

/**
 * Created by: xmg
 * 2018/3/26 22:42
 */
@Getter
public enum ProductStatusEum implements CodeEnum{
    UP(0, "在架"),
    DOWN(1, "下架"),
    ;

    private Integer code;

    private String message;

    ProductStatusEum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
