package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;
import com.imooc.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by: xmg
 * 2018/4/1 19:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setUsername("lala");
        sellerInfo.setPassword("1djkzbh");
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setOpenid("abcd");

        SellerInfo result = repository.save(sellerInfo);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByOpenid() {
        SellerInfo result = repository.findByOpenid("abcd");
        Assert.assertEquals("abcd",result.getOpenid());
    }
}