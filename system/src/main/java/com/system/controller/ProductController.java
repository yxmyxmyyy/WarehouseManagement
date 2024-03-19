package com.system.controller;

import com.api.domain.dto.ProductDTO;
import com.api.domain.po.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.domain.R;
import com.common.exception.BadRequestException;
import com.common.exception.BizIllegalException;
import com.system.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "产品控制器，处理产品的增删改查操作")
public class ProductController {

    private final IProductService ProductService;

    //分页查询
    @Operation(summary = "分页查询产品", description = "根据条件分页查询产品信息")
    @PostMapping("/find")
    public R<Page<Product>> find(@RequestBody ProductDTO ProductDTO, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        try {
            return R.ok(ProductService.find(ProductDTO,pageNum, pageSize));
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    //新增或修改
    @Operation(summary = "新增或修改产品", description = "根据传入的产品信息进行新增或修改操作")
    @PostMapping("/saveOrUpdate")
    public R<String> saveOrUpdate(@RequestBody Product Product) {
        try {
            Product.setCreatedTime(null);
            Product.setUpdatedTime(null);
            ProductService.saveOrUpdate(Product);
            return R.ok("ok");
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Operation(summary = "删除产品", description = "根据产品ID删除单个产品")
    //删除一个
    @DeleteMapping("/delete")
    public R<String> deleteOne(@RequestParam Serializable id) {
        try {
            ProductService.removeById(id);
            return R.ok("删除成功");
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    
}
