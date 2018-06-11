package com.imooc.service.impl;

import com.imooc.dataobject.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by: xmg
 * 2018/4/2 9:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoServiceImplTest {

    private static final String OPENID = "abcd";

    private SellerServiceImpl sellerService;
    @Test
    public void findSellInfoByOpenid() {
        SellerInfo result = sellerService.findSellerInfoByOpenid(OPENID);
        Assert.assertEquals(OPENID,result.getOpenid());
    }
}