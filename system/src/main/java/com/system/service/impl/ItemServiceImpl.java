package com.system.service.impl;


import com.api.domain.dto.ItemDTO;
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Autowired
    private ItemMapper itemMapper;

    private final IProductService ProductService;

    public Item findExistingProductIds(Long storeId,Long productId){
        QueryWrapper<Item> qw = new QueryWrapper<>();
        qw.eq("store_id", storeId);
        qw.eq("product_id", productId);
        return getOne(qw);
    }

    @Transactional
    public Item addStockOrNewItem(Long storeId, Long productId, Integer quantity) {
        Item existingItem = findExistingProductIds(storeId, productId);
        if (existingItem != null) {
            // 商品存在
            itemMapper.increaseStock(storeId, productId, quantity);
        } else {
            // 商品不存在
            Item newItem = new Item();
            newItem.setStoreId(storeId);
            newItem.setProductId(productId);
            newItem.setStock(quantity);
            this.save(newItem);
        }
        return(findExistingProductIds(storeId, productId));
    }

    @Transactional
    public Item decreaseStockIfNeeded(Long storeId, Long productId, Integer quantity) {
        Item item = findExistingProductIds(storeId, productId);
        if (item != null && item.getStock() >= quantity) {
            itemMapper.decreaseStock(storeId, productId, quantity);
        } else {
            throw new BadRequestException("库存不足");
        }
        return(findExistingProductIds(storeId, productId));
    }

    public ItemDTO find(Long productId) {
        Product productInfo = ProductService.getById(productId);
        Integer totalStock = itemMapper.findTotalStock(productId);
        BigDecimal totalPrice;
        try {
            totalPrice = productInfo.getPrice().multiply(new BigDecimal(totalStock));
        } catch (Exception e) {
            throw new BadRequestException("商品不存在");
        }

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductId(productId);
        itemDTO.setProductName(productInfo.getName());
        itemDTO.setTotalStock(totalStock);
        itemDTO.setPrice(productInfo.getPrice());
        itemDTO.setTotalPrice(totalPrice);

        return itemDTO;
    }

}
