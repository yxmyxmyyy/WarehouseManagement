package com.system.service;

import com.api.domain.dto.ProductDTO;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IProductService extends IService<Product> {

    Page<Product> find(ProductDTO productDTO, Integer pageNum, Integer pageSize);

    Product findByName(String name);
}
