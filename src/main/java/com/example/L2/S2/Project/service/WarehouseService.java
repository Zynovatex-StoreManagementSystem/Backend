package com.example.L2.S2.Project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.L2.S2.Project.dao.request.WarehouseRequest;
import com.example.L2.S2.Project.model.Warehouse;
import com.example.L2.S2.Project.repository.WarehouseRepository;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    private Map<Long, Warehouse> warehouseCache = new HashMap<>();

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse getWarehouseById(Long id) {
        if (!warehouseCache.containsKey(id)) {
            Warehouse warehouse = warehouseRepository.findById(id).orElse(null);
            if (warehouse == null) {
                return null;
            }
            warehouseCache.put(id, warehouse);
        }
        return warehouseCache.get(id);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public List<WarehouseRequest> getAllWarehouseRequests() {
        return warehouseRepository.findAll().stream()
                .map(this::toWarehouseRequest)
                .collect(Collectors.toList());
    }

    public WarehouseRequest getWarehouseRequestById(Long warehouseId) {
        Optional<Warehouse> w = warehouseRepository.findById(warehouseId);
        if (w.isPresent()) {
            Warehouse warehouse = w.get();
            return toWarehouseRequest(warehouse);
        }
        return null;

    }

    public WarehouseRequest toWarehouseRequest(Warehouse warehouse) {
        return new WarehouseRequest(
                warehouse.getId(),
                warehouse.getLocation(),
                warehouse.getManagerId(),
                warehouse.getManagerName());
    }

    public Warehouse addWarehouse(Warehouse warehouse) {
        try {
            return warehouseRepository.save(warehouse);
        } catch (Exception e) {
            return null;
        }
    }

    public Warehouse updateWarehouseById(Long warehouseId, WarehouseRequest warehouse) {
        try {
            Optional<Warehouse> w = warehouseRepository.findById(warehouseId);
            System.out.println("warehouse: " + warehouse);
            if (w.isPresent()) {
                if (warehouse.getLocation() != null && !"".equalsIgnoreCase(warehouse.getLocation())) {
                    System.out.println("update location");
                    warehouseRepository.updateLocation(warehouseId, warehouse.getLocation());
                }
                if (warehouse.getManagerId() != null && !"".equalsIgnoreCase(warehouse.getManagerId())) {
                    System.out.println("update manager id");
                    warehouseRepository.updateManagerId(warehouseId, warehouse.getManagerId());
                }
                if (warehouse.getManagerName() != null && !"".equalsIgnoreCase(warehouse.getManagerName())) {
                    System.out.println("update manager name");
                    warehouseRepository.updateManagerName(warehouseId, warehouse.getManagerName());
                }
                return warehouseRepository.findById(warehouseId).orElse(null);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteWarehouseById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId).map(warehouse -> {
            warehouseRepository.delete(warehouse);
            return true;
        }).orElse(false);
    }
}
