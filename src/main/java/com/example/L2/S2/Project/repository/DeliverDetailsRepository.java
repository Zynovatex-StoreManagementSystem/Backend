package com.example.L2.S2.Project.repository;

import com.example.L2.S2.Project.model.DeliverDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverDetailsRepository extends JpaRepository<DeliverDetails, Long> {
}
