package net.javaguides.warehouse.controller;

import lombok.AllArgsConstructor;
import net.javaguides.warehouse.dto.WarehouseDto;
import net.javaguides.warehouse.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {


    private WarehouseService warehouseService;

    //Build Add Warehouse REST API
    @PostMapping
    public ResponseEntity<WarehouseDto> createWarehouse(@RequestBody WarehouseDto warehouseDto)
    {
        WarehouseDto savedWarehouse =  warehouseService.createWarehouse(warehouseDto);
        return new ResponseEntity<>(savedWarehouse, HttpStatus.CREATED);
    }

    //Build Get Warehouse REST API
    @GetMapping("{id}")
    public ResponseEntity<WarehouseDto> getWarehouseById(@PathVariable("id") Long warehouseId)
    {
       WarehouseDto warehouseDto =  warehouseService.getWarehouseByID(warehouseId);
       return ResponseEntity.ok(warehouseDto);

    }

    //Build Get All Warehouses REST API
    @GetMapping
    public ResponseEntity<List<WarehouseDto>> getAllWarehouses()
    {
       List<WarehouseDto> warehouses =  warehouseService.getAllWarehouses();
       return ResponseEntity.ok(warehouses);
    }

    //Build Update Warehouse REST API
    @PutMapping("{id}")
    public ResponseEntity<WarehouseDto> updateWarehouse(@PathVariable("id") Long warehouseId,
                                                        @RequestBody WarehouseDto updatedWarehouse) {
        WarehouseDto warehouseDto = warehouseService.updateWarehouse(warehouseId, updatedWarehouse);
        return ResponseEntity.ok(warehouseDto);
    }

    // Build Delete Warehouse REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable("id") Long warehouseId){
        warehouseService.deleteWarehouse(warehouseId);
        return ResponseEntity.ok("Warehouse is deleted successfully");

    }


}
