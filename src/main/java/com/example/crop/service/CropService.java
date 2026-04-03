package com.example.crop.service;

import com.example.crop.entity.Crop;
import java.util.List;

/**
 * 农作物业务逻辑接口
 * 定义农作物的业务操作方法
 */
public interface CropService {

    /**
     * 添加农作物
     * @param crop 农作物实体对象
     */
    void add(Crop crop);

    /**
     * 更新农作物信息
     * @param crop 农作物实体对象
     */
    void update(Crop crop);

    /**
     * 根据ID删除农作物
     * @param id 农作物ID
     */
    void delete(Long id);

    /**
     * 根据ID查询农作物
     * @param id 农作物ID
     * @return 农作物实体对象，如果不存在返回null
     */
    Crop getById(Long id);

    /**
     * 查询所有农作物
     * @return 农作物列表
     */
    List<Crop> getAll();
}
