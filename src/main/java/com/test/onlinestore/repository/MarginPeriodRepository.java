package com.test.onlinestore.repository;

import com.test.onlinestore.model.DetailTransaction;
import com.test.onlinestore.model.MarginPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarginPeriodRepository extends JpaRepository<MarginPeriod, Long> {
}
