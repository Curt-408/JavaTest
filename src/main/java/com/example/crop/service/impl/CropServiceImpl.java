package com.example.crop.service.impl;

import com.example.crop.entity.Crop;
import com.example.crop.mapper.CropMapper;
import com.example.crop.service.CropService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CropServiceImpl implements CropService {

    private final CropMapper cropMapper;

    public CropServiceImpl(CropMapper cropMapper) {
        this.cropMapper = cropMapper;
    }

    @Override
    public void add(Crop crop) {
        cropMapper.insert(crop);
    }

    @Override
    public void update(Crop crop) {
        cropMapper.update(crop);
    }

    @Override
    public void delete(Long id) {
        cropMapper.delete(id);
    }

    @Override
    public Crop getById(Long id) {
        return cropMapper.selectById(id);
    }

    @Override
    public List<Crop> getAll() {
        return cropMapper.selectAll();
    }
}
