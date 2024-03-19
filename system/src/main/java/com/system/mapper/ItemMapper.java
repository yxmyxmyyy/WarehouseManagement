package com.system.mapper;

import com.api.domain.po.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ItemMapper extends BaseMapper<Item> {

    @Select("SELECT SUM(stock) AS totalStock" +
            " FROM items" +
            " WHERE product_id = #{productId}")
    Integer findTotalStock(Long productId);

    //加库存
    @Update("UPDATE items" +
            " SET stock = stock + #{quantity}" +
            " WHERE store_id = #{storeId}" +
            " AND product_id = #{productId}")
    void increaseStock(@Param("storeId") Long storeId, @Param("productId") Long productId, @Param("quantity") Integer quantity);

    //减库存
    @Update("UPDATE items" +
            " SET stock = stock - #{quantity}" +
            " WHERE store_id = #{storeId}" +
            " AND product_id = #{productId}")
    void decreaseStock(@Param("storeId") Long storeId, @Param("productId") Long productId, @Param("quantity") Integer quantity);

}
