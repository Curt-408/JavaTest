package com.example.crop.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 农作物实体类
 * 对应数据库中的 crop 表
 */
@Data
public class Crop {
    /** 农作物ID，主键自增 */
    private Long id;

    /** 农作物名称 */
    private String name;

    /** 农作物类别（如：粮食、蔬菜、水果等） */
    private String category;

    /** 生长周期，单位为天 */
    private Integer growthCycle;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;
}
