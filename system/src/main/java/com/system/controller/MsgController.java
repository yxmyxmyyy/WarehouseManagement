package com.system.controller;

import com.api.domain.dto.ItemDTO;
import com.api.domain.dto.MsgDTO;
import com.api.domain.dto.ProductDTO;
import com.api.domain.po.Msg;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.domain.R;
import com.common.exception.BadRequestException;
import com.system.service.IItemService;
import com.system.service.IMsgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/msg")
@RequiredArgsConstructor
@Tag(name = "Msg Controller", description = "消息控制器，发送，查询消息")
public class MsgController {

    private final IMsgService msgService;

    @Operation(summary = "分页查询产品", description = "根据条件分页查询消息")
    @PostMapping("/find")
    public R<Page<Msg>> find(@RequestBody MsgDTO MsgDTO, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        try {
            return R.ok(msgService.find(MsgDTO,pageNum, pageSize));
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Operation(summary = "读取选定消息", description = "读取消息，并改变状态")
    @GetMapping("/read")
    public R<Msg> read(@RequestParam Long id) {
        try {
            Msg msg = new Msg();
            msg.setId(id);
            msg.setStatus(1);
            msgService.updateById(msg);
            return R.ok(msgService.getById(id));
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    
}
