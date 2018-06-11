package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * Created by: xmg
 * 2018/3/29 12:56
 */

public interface BuyerService {
    //查一个订单
    OrderDTO findOneOrder(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
