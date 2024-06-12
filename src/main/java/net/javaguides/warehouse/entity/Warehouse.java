package net.javaguides.warehouse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="warehouses")



public class Warehouse {

    @Id
    private long warehouseId;

    @Column(name="Location")
    private String location;

    @Column(name="Manager Id",nullable = false,unique = true)
    private String managerId;

    @Column(name="Manager Name")
    private String managerName;
}
