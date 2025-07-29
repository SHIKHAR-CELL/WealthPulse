package com.InvestoTracker.repository;

import com.InvestoTracker.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByUserUsername(String username);
}
