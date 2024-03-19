package com.system.controller;

import com.api.domain.dto.LedgerDTO;
import com.api.domain.po.Item;
import com.api.domain.po.Ledger;
import com.api.domain.po.Msg;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.domain.R;
import com.common.exception.BadRequestException;
import com.system.service.IItemService;
import com.system.service.ILedgerService;
import com.system.service.IMsgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/ledger")
@RequiredArgsConstructor
@Tag(name = "Ledger Controller", description = "账目控制器，处理入库和出库操作")
public class LedgerController {

    private final IItemService itemService;

    private final ILedgerService ledgerService;

    private final IMsgService msgService;

    //入库
    @Operation(summary = "入库操作", description = "将指定数量的产品入库")
    @PostMapping("/in")
    public R<Item> in(@RequestBody Ledger ledger) {
        Item item;
        try {
            if (ledger.getQuantity()>0){
                item = itemService.addStockOrNewItem(ledger.getStoreId(), ledger.getProductId(), ledger.getQuantity());
            }else {
                throw new BadRequestException("入库数量必须大于0");
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        try {
            ledger.setType("IN");
            ledgerService.save(ledger);
            return R.ok(item);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping("/out")
    @Operation(summary = "出库操作", description = "将指定数量的产品出库")
    public R<Item> out(@RequestBody Ledger ledger) {
        Item item;
        try {
            if (ledger.getQuantity()>0){
                item = itemService.decreaseStockIfNeeded(ledger.getStoreId(), ledger.getProductId(), ledger.getQuantity());
            }else {
                throw new BadRequestException("出库数量必须大于0");
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        try {
            Msg msg = new Msg();
            msg.setStatus(0);
            msg.setText("出库内容:"+ledger.getProductId()+","+"数量:"+ledger.getQuantity());
            ledger.setType("OUT");
            ledgerService.save(ledger);
            msgService.save(msg);
            return R.ok(item);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    //分页查询
    @PostMapping("/find")
    @Operation(summary = "分页查询账目", description = "根据条件分页查询账目信息")
    public R<Page<Ledger>> find(@RequestBody LedgerDTO LedgerDTO, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        try {
            return R.ok(ledgerService.find(LedgerDTO,pageNum, pageSize));
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    
}
