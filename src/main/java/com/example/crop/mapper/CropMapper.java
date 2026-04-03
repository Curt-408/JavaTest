package com.example.crop.mapper;

import com.example.crop.entity.Crop;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 农作物数据访问层接口
 * 负责与数据库进行交互，执行增删改查操作
 */
@Mapper
public interface CropMapper {

    /**
     * 插入农作物
     * @param crop 农作物实体对象
     */
    @Insert("INSERT INTO crop (name, category, growth_cycle) VALUES (#{name}, #{category}, #{growthCycle})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Crop crop);

    /**
     * 更新农作物信息
     * @param crop 农作物实体对象
     */
    @Update("UPDATE crop SET name=#{name}, category=#{category}, growth_cycle=#{growthCycle}, update_time=NOW() WHERE id=#{id}")
    void update(Crop crop);

    /**
     * 根据ID删除农作物
     * @param id 农作物ID
     */
    @Delete("DELETE FROM crop WHERE id=#{id}")
    void delete(Long id);

    /**
     * 根据ID查询农作物
     * @param id 农作物ID
     * @return 农作物实体对象，如果不存在返回null
     */
    @Select("SELECT * FROM crop WHERE id=#{id}")
    Crop selectById(Long id);

    /**
     * 查询所有农作物
     * @return 农作物列表
     */
    @Select("SELECT * FROM crop")
    List<Crop> selectAll();
}
