package com.example.crop.controller;

import com.example.crop.entity.Crop;
import com.example.crop.service.CropService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crop")
@Tag(name = "Crop Management", description = "Crop CRUD operations")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PostMapping
    @Operation(summary = "Add crop", description = "Add a new crop")
    public ResponseEntity<Void> add(@RequestBody Crop crop) {
        cropService.add(crop);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get crop by ID", description = "Retrieve a single crop")
    public ResponseEntity<Crop> getById(@PathVariable Long id) {
        Crop crop = cropService.getById(id);
        if (crop == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(crop);
    }

    @GetMapping("/list")
    @Operation(summary = "Get all crops", description = "Retrieve all crops")
    public ResponseEntity<List<Crop>> getAll() {
        return ResponseEntity.ok(cropService.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update crop", description = "Update an existing crop")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Crop crop) {
        crop.setId(id);
        cropService.update(crop);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete crop", description = "Delete a crop by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cropService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
