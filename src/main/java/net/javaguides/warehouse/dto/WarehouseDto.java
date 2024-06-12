package net.javaguides.warehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {

    private long warehouseId;
    private String location;
    private String managerId;
    private String managerName;
}
