package net.javaguides.warehouse.service;

import net.javaguides.warehouse.dto.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    WarehouseDto createWarehouse(WarehouseDto warehouseDto);

    WarehouseDto getWarehouseByID(Long warehouseId);

    List<WarehouseDto> getAllWarehouses();

    WarehouseDto updateWarehouse(Long warehouseId, WarehouseDto updatedWarehouse);

    void deleteWarehouse(Long warehouseId);


}
