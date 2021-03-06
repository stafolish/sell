package com.imooc.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.enums.ProductStatusEum;
import com.imooc.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by: xmg
 * 2018/3/26 21:45
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable{

    private static final long serialVersionUID = 6291790101932167185L;

    @Id
    private String productId;

    /*名字*/
    private String productName;

    /*单价*/
    private BigDecimal productPrice;

    /*库存*/
    private Integer productStock;

    /*描述*/
    private String productDescription;

    /*小图*/
    private String productIcon;

    /*状态，0正常1下架*/
    private Integer productStatus = ProductStatusEum.UP.getCode();

    /*类目编号*/
    private Integer categoryType;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEum.class);
    }

}
