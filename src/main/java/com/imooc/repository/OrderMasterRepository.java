package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: xmg
 * 2018/3/28 12:53
 */

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>{
    Page<OrderMaster>  findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
