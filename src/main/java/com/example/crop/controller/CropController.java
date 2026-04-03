package com.example.crop.controller;

import com.example.crop.entity.Crop;
import com.example.crop.service.CropService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 农作物REST接口层
 * 提供HTTP接口，供前端或其他服务调用
 */
@RestController
@RequestMapping("/crop")
@Tag(name = "Crop Management", description = "农作物管理CRUD操作")
public class CropController {

    /** 注入Service依赖，处理业务逻辑 */
    private final CropService cropService;

    /** 构造方法注入CropService */
    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    /**
     * 添加农作物
     * @param crop 农作物实体对象（JSON格式）
     * @return 200 OK
     */
    @PostMapping
    @Operation(summary = "添加农作物", description = "添加一个新的农作物到数据库")
    public ResponseEntity<Void> add(@RequestBody Crop crop) {
        cropService.add(crop);
        return ResponseEntity.ok().build();
    }

    /**
     * 根据ID查询农作物
     * @param id 农作物ID
     * @return 农作物实体对象，如果不存在返回404
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询农作物", description = "根据农作物ID查询详细信息")
    public ResponseEntity<Crop> getById(
            @Parameter(description = "农作物ID") @PathVariable Long id) {
        Crop crop = cropService.getById(id);
        if (crop == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(crop);
    }

    /**
     * 查询所有农作物
     * @return 农作物列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有农作物", description = "获取数据库中所有农作物的列表")
    public ResponseEntity<List<Crop>> getAll() {
        return ResponseEntity.ok(cropService.getAll());
    }

    /**
     * 更新农作物信息
     * @param id 农作物ID
     * @param crop 农作物实体对象（JSON格式）
     * @return 200 OK
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新农作物", description = "根据ID更新农作物的信息")
    public ResponseEntity<Void> update(
            @Parameter(description = "农作物ID") @PathVariable Long id,
            @RequestBody Crop crop) {
        crop.setId(id);
        cropService.update(crop);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除农作物
     * @param id 农作物ID
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除农作物", description = "根据ID删除农作物")
    public ResponseEntity<Void> delete(
            @Parameter(description = "农作物ID") @PathVariable Long id) {
        cropService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
