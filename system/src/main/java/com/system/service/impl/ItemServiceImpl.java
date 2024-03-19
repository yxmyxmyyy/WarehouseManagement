package com.system.service.impl;


import com.api.domain.po.Item;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.exception.BadRequestException;
import com.system.mapper.ItemMapper;
import com.system.mapper.ProductMapper;
import com.system.service.IItemService;
import com.system.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Autowired
    private ItemMapper itemMapper;
    public Item findExistingProductIds(Long storeId,Long productId){
        QueryWrapper<Item> qw = new QueryWrapper<>();
        qw.eq("store_id", storeId);
        qw.eq("product_id", productId);
        return getOne(qw);
    }

    @Transactional
    public Item addStockOrNewItem(Long storeId, Long productId, Long quantity) {
        Item existingItem = findExistingProductIds(storeId, productId);
        if (existingItem != null) {
            // 商品存在，增加库存
            itemMapper.increaseStock(storeId, productId, quantity);
        } else {
            // 商品不存在，新增商品
            Item newItem = new Item();
            newItem.setStoreId(storeId);
            newItem.setProductId(productId);
            newItem.setStock(quantity); // 假设这是新增商品时的初始库存量
            // 使用MyBatis Plus的save方法新增商品
            this.save(newItem);
        }
        return(findExistingProductIds(storeId, productId));
    }

    @Transactional
    public Item decreaseStockIfNeeded(Long storeId, Long productId, Long quantity) {
        Item item = findExistingProductIds(storeId, productId);
        if (item != null && item.getStock() >= quantity) {
            itemMapper.decreaseStock(storeId, productId, quantity);
        } else {
            throw new BadRequestException("库存不足");
        }
        return(findExistingProductIds(storeId, productId));
    }
}
