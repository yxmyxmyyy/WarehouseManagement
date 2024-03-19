package com.system.service;

import com.api.domain.po.Item;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IItemService extends IService<Item> {

    Item findExistingProductIds(Long storeId,Long productId);
    Item decreaseStockIfNeeded(Long storeId, Long productId, Long quantity);
    Item addStockOrNewItem(Long storeId, Long productId, Long quantity);
}
