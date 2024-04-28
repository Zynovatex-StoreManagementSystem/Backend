package com.example.L2.S2.Project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int phone;

    @OneToMany(mappedBy = "deliver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliverDetails> details;

    public Driver() {
        this.details = new ArrayList<>();
    }

    public void addOrder(DeliverDetails detail) {
        details.add(detail);
        detail.setDriver(this);
    }
}