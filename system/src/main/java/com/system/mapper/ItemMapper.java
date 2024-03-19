package com.system.mapper;

import com.api.domain.po.Item;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

@Mapper
public interface ItemMapper extends BaseMapper<Item> {

    //加库存
    @Update("UPDATE items" +
            " SET stock = stock + #{quantity}" +
            " WHERE store_id = #{storeId}" +
            " AND product_id = #{productId}")
    int increaseStock(@Param("storeId") Long storeId, @Param("productId") Long productId, @Param("quantity") Long quantity);

    //减库存
    @Update("UPDATE items" +
            " SET stock = stock - #{quantity}" +
            " WHERE store_id = #{storeId}" +
            " AND product_id = #{productId}")
    int decreaseStock(@Param("storeId") Long storeId, @Param("productId") Long productId, @Param("quantity") Long quantity);

}
