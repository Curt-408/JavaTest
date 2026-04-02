package com.example.crop.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Crop {
    private Long id;
    private String name;
    private String category;
    private Integer growthCycle;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
