package com.example.crop.service.impl;

import com.example.crop.entity.Crop;
import com.example.crop.mapper.CropMapper;
import com.example.crop.service.CropService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 农作物业务逻辑实现类
 * 实现具体的业务操作，调用Mapper层进行数据访问
 */
@Service
public class CropServiceImpl implements CropService {

    /** 注入Mapper依赖，用于数据库访问 */
    private final CropMapper cropMapper;

    /** 构造方法注入CropMapper */
    public CropServiceImpl(CropMapper cropMapper) {
        this.cropMapper = cropMapper;
    }

    /**
     * 添加农作物
     * @param crop 农作物实体对象
     */
    @Override
    public void add(Crop crop) {
        cropMapper.insert(crop);
    }

    /**
     * 更新农作物信息
     * @param crop 农作物实体对象
     */
    @Override
    public void update(Crop crop) {
        cropMapper.update(crop);
    }

    /**
     * 根据ID删除农作物
     * @param id 农作物ID
     */
    @Override
    public void delete(Long id) {
        cropMapper.delete(id);
    }

    /**
     * 根据ID查询农作物
     * @param id 农作物ID
     * @return 农作物实体对象
     */
    @Override
    public Crop getById(Long id) {
        return cropMapper.selectById(id);
    }

    /**
     * 查询所有农作物
     * @return 农作物列表
     */
    @Override
    public List<Crop> getAll() {
        return cropMapper.selectAll();
    }
}
