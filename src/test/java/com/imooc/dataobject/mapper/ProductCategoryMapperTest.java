package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by: xmg
 * 2018/4/2 17:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "新奇");
        map.put("category_type", 101);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectTest() {
        ProductCategory productCategory = mapper.findByCategoryType(101);

        Assert.assertNotNull(productCategory);
    }

    @Test
    public void updateBycategoryName() {
        int result = mapper.updateBycategoryName("hahah", 101);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(101);
        productCategory.setCategoryName("小姐姐最爱");
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }


    @Test
    public void selectByCategoryType(){
        ProductCategory productCategory = mapper.selectByCategoryType(101);
        Assert.assertNotNull(productCategory);
    }

}