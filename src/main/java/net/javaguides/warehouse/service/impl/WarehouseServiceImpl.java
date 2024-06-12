package net.javaguides.warehouse.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.warehouse.dto.WarehouseDto;
import net.javaguides.warehouse.entity.Warehouse;
import net.javaguides.warehouse.exception.ResourceNotFoundException;
import net.javaguides.warehouse.mapper.WarehouseMapper;
import net.javaguides.warehouse.repository.WarehouseRepository;
import net.javaguides.warehouse.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private WarehouseRepository warehouseRepository;

    @Override
    public WarehouseDto createWarehouse(WarehouseDto warehouseDto) {

        Warehouse warehouse = WarehouseMapper.mapToWarehouse(warehouseDto);
        Warehouse savedWarehouse =   warehouseRepository.save(warehouse);
        return WarehouseMapper.mapToWarehouseDto(savedWarehouse);


    }

    @Override
    public WarehouseDto getWarehouseByID(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse is not exists with the given id: " + warehouseId));

        return WarehouseMapper.mapToWarehouseDto(warehouse);
    }

    @Override
    public List<WarehouseDto> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream()
                .map(warehouse -> WarehouseMapper.mapToWarehouseDto(warehouse))
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseDto updateWarehouse(Long warehouseId, WarehouseDto updatedWarehouse) {

        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(
                () -> new ResourceNotFoundException("Warehouse is not exists with given id: " + warehouseId)
        );

        warehouse.setLocation(updatedWarehouse.getLocation());
        warehouse.setManagerId(updatedWarehouse.getManagerId());
        warehouse.setManagerName(updatedWarehouse.getManagerName());

        Warehouse updatedWarehouseObj =  warehouseRepository.save(warehouse);
        return WarehouseMapper.mapToWarehouseDto(updatedWarehouseObj);
    }

    @Override
    public void deleteWarehouse(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(
                () -> new ResourceNotFoundException("Warehouse is not exists with given id: " + warehouseId)
        );

        warehouseRepository.deleteById(warehouseId);
    }
}
