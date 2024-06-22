package com.example.L2.S2.Project.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String userEmail;
    private Long driverId;
    private LocalDate returnDate;
    private List<OrderItemRequest> orderItems;
}
