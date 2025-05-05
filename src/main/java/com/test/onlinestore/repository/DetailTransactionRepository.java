package com.test.onlinestore.repository;

import com.test.onlinestore.model.DetailTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailTransactionRepository extends JpaRepository<DetailTransaction, Long> {

}
