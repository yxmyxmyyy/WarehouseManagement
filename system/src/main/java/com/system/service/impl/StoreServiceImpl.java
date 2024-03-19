package com.system.service.impl;


import com.api.domain.po.Product;
import com.api.domain.po.Store;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.ProductMapper;
import com.system.mapper.StoreMapper;
import com.system.service.IProductService;
import com.system.service.IStoreService;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements IStoreService {

}
