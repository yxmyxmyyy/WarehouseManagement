package com.system.service.impl;


import com.api.domain.dto.ProductDTO;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.ProductMapper;
import com.system.service.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    public Page<Product> find(ProductDTO productDTO, Integer pageNum, Integer pageSize) {
        Page<Product> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Product> qw = new QueryWrapper<>();

        if (productDTO.getId() != null){
            qw.eq("id", productDTO.getId());
        }
        if (productDTO.getName() != null && !productDTO.getName().isEmpty()) {
            qw.eq("name", productDTO.getName());
        }
        qw.orderByDesc("created_time");
        return page(page, qw);
    }

    public Product findByName(String name) {
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.eq("name", name);
        return getOne(qw);
    }
}
