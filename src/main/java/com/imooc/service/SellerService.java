package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 * Created by: xmg
 * 2018/4/2 9:39
 */

public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
