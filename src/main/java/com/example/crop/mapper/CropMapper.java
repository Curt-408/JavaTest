package com.example.crop.mapper;

import com.example.crop.entity.Crop;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CropMapper {

    @Insert("INSERT INTO crop (name, category, growth_cycle) VALUES (#{name}, #{category}, #{growthCycle})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Crop crop);

    @Update("UPDATE crop SET name=#{name}, category=#{category}, growth_cycle=#{growthCycle}, update_time=NOW() WHERE id=#{id}")
    void update(Crop crop);

    @Delete("DELETE FROM crop WHERE id=#{id}")
    void delete(Long id);

    @Select("SELECT id, name, category, growth_cycle AS growthCycle, create_time AS createTime, update_time AS updateTime FROM crop WHERE id=#{id}")
    Crop selectById(Long id);

    @Select("SELECT id, name, category, growth_cycle AS growthCycle, create_time AS createTime, update_time AS updateTime FROM crop")
    List<Crop> selectAll();
}
