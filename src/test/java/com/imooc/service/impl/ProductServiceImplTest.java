package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by: xmg
 * 2018/3/26 22:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findById() {

        ProductInfo productInfo = productService.findById("1234567");
        Assert.assertEquals("1234567",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        // System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234569");
        productInfo.setProductName("拉面");
        productInfo.setProductPrice(new BigDecimal(14));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("有劲道的拉面");
        productInfo.setProductIcon("http:xxx.jpg");
        productInfo.setProductStatus(ProductStatusEum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void onsale(){
        ProductInfo result = productService.onSale("1234567");
        Assert.assertEquals(ProductStatusEum.UP,result.getProductStatusEnum());
    }

    @Test
    public void offsale(){
        ProductInfo result = productService.offSale("1234567");
        Assert.assertEquals(ProductStatusEum.DOWN,result.getProductStatusEnum());
    }
}