package com.system.controller;

import com.api.domain.dto.ItemDTO;
import com.api.domain.dto.LedgerDTO;
import com.api.domain.po.Item;
import com.api.domain.po.Ledger;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.domain.R;
import com.common.exception.BadRequestException;
import com.system.service.IItemService;
import com.system.service.ILedgerService;
import com.system.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/item")
@RequiredArgsConstructor
@Tag(name = "Item Controller", description = "库存控制器，处理商品统计")
public class ItemController {

    private final IItemService itemService;

    @Operation(summary = "统计特定商品", description = "返回商品总价，库存数量和商品信息")
    @GetMapping("/find")
    public R<ItemDTO> find(@RequestParam Long productId) {
        try {
            ItemDTO itemDTO = itemService.find(productId);
            return R.ok(itemDTO);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    
}
