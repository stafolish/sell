package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by: xmg
 * 2018/4/2 17:30
 */

public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type)values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);


    @Select("select * from product_category where category_type=#{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name=#{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Update("update product_category set category_name =#{categoryName} where category_type=#{categoryType}")
    int updateBycategoryName(@Param("categoryName") String categoryName,
                             @Param("categoryType")int categoryType);

    @Update("update product_category set category_name =#{categoryName} where category_type=#{categoryType}")
    int updateByObject(ProductCategory productCategory);

    
    ProductCategory selectByCategoryType(Integer categoryType);
}
