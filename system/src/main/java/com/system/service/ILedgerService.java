package com.system.service;

import com.api.domain.dto.LedgerDTO;
import com.api.domain.po.Ledger;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ILedgerService extends IService<Ledger> {

    Page<Ledger> find(LedgerDTO ledgerDTO, Integer pageNum, Integer pageSize);


}
