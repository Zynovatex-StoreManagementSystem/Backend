package net.javaguides.warehouse.mapper;

import net.javaguides.warehouse.dto.WarehouseDto;
import net.javaguides.warehouse.entity.Warehouse;

public class WarehouseMapper {

    public static WarehouseDto mapToWarehouseDto(Warehouse warehouse)
    {
        return new WarehouseDto(
                warehouse.getWarehouseId(),
                warehouse.getLocation(),
                warehouse.getManagerId(),
                warehouse.getManagerName()
        );
    }

    public static Warehouse mapToWarehouse(WarehouseDto warehouseDto)
    {
        return new Warehouse(
                warehouseDto.getWarehouseId(),
                warehouseDto.getLocation(),
                warehouseDto.getManagerId(),
                warehouseDto.getManagerName()
        );
    }
}
