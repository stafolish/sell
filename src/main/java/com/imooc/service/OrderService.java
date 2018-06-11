package com.imooc.service;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by: xmg
 * 2018/3/28 14:23
 */

public interface OrderService {
    // 创建订单
    OrderDTO create(OrderDTO orderDTO);

    // 查询单个订单
    OrderDTO findByOrderId(String orderId);

    // 查询订单列表
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    // 取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    // 完结/确认订单
    OrderDTO finish(OrderDTO orderDTO);

    // 支付订单
    OrderDTO paid(OrderDTO orderDTO);

    // 查询订单列表
    Page<OrderDTO> findList(Pageable pageable);

}
