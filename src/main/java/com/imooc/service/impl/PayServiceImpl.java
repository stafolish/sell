package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.imooc.util.JsonUtil;
import com.imooc.util.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by: xmg
 * 2018/3/30 10:33
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";


    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信支付】 发起支付，request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 发起支付，response={}", JsonUtil.toJson(payResponse));

        return payResponse;

    }

    @Override
    public PayResponse notify(String notifyData) {
        // 1.验证签名(SDK完成)
        // 2.支付状态(SDK完成)
        // 3.支付金额
        // 4.支付人（下单人==支付人）（可选）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知，response={}", JsonUtil.toJson(payResponse));

        // 修改订单支付状态
        OrderDTO orderDTO = orderService.findByOrderId(payResponse.getOrderId());

        // 判断金额是否一致(0.10 0.1)
        if (!(orderDTO.getOrderAmount().doubleValue() - payResponse.getOrderAmount() < MathUtil.MONEY_RANGE)) {
            log.error("【微信支付】 异步通知，订单金额不一致，orderId={},微信通知金额={},系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改订单支付状态
        orderService.paid(orderDTO);

        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 request={}", JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 response={}", JsonUtil.toJson(refundResponse));

        return refundResponse;
    }
}
