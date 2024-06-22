package com.example.L2.S2.Project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.L2.S2.Project.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Transactional
    @Modifying
    @Query("update Warehouse w set w.location = :location where w.id = :id")
    void updateLocation(@Param("id") Long id, @Param("location") String location);

    @Transactional
    @Modifying
    @Query("update Warehouse w set w.managerId = :managerId where w.id = :id")
    void updateManagerId(@Param("id") Long id, @Param("managerId") String managerId);

    @Transactional
    @Modifying
    @Query("update Warehouse w set w.managerName = :managerName where w.id = :warehouseId")
    void updateManagerName(@Param("warehouseId") Long warehouseId, @Param("managerName") String managerName);
}