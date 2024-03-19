package com.system.service.impl;


import com.api.domain.dto.LedgerDTO;
import com.api.domain.po.Ledger;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.exception.BadRequestException;
import com.system.mapper.LedgerMapper;
import com.system.mapper.ProductMapper;
import com.system.service.ILedgerService;
import com.system.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class LedgerServiceImpl extends ServiceImpl<LedgerMapper, Ledger> implements ILedgerService {

    private final IProductService ProductService;
    public Page<Ledger> find(LedgerDTO ledgerDTO, Integer pageNum, Integer pageSize) {
        Long productId = null;
        if (ledgerDTO.getProductName() != null && !ledgerDTO.getProductName().isEmpty()) {
            try{
                productId = ProductService.findByName(ledgerDTO.getProductName()).getId();
            } catch (Exception e){
                throw new BadRequestException("商品不存在");
            }
        }
        Page<Ledger> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Ledger> qw = new QueryWrapper<>();
        qw.eq("type", "IN");
        if (productId != null) {
            qw.eq("product_id", productId);
        }
        if (ledgerDTO.getCreatedTime() != null) {
            qw.ge("created_time", ledgerDTO.getCreatedTime());
        }
        return page(page, qw);
    }
}
