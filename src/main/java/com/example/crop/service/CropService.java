package com.example.crop.service;

import com.example.crop.entity.Crop;
import java.util.List;

public interface CropService {
    void add(Crop crop);
    void update(Crop crop);
    void delete(Long id);
    Crop getById(Long id);
    List<Crop> getAll();
}
