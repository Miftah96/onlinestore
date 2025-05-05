package com.test.onlinestore.service;

import com.test.onlinestore.model.DetailTransaction;
import com.test.onlinestore.model.DetailTransaction;
import com.test.onlinestore.repository.DetailTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailTransactionService {
    private final DetailTransactionRepository repository;

    public List<DetailTransaction> findAll() { return repository.findAll(); }
    public DetailTransaction save(DetailTransaction dt) { return repository.save(dt); }
    public DetailTransaction findById(Long id) { return repository.findById(id).orElse(null); }
    public DetailTransaction updateDetailTransaction(Long id, DetailTransaction updatedDetailTransaction) {
        return repository.findById(id)
                .map(existingDetailTransaction -> {
                    existingDetailTransaction.setTransaction(updatedDetailTransaction.getTransaction());
                    existingDetailTransaction.setProduct(updatedDetailTransaction.getProduct());
                    existingDetailTransaction.setSubtotal(updatedDetailTransaction.getSubtotal());
                    existingDetailTransaction.setQuantity(updatedDetailTransaction.getQuantity());
                    return repository.save(existingDetailTransaction);
                })
                .orElse(null);
    }
    public void deleteById(Long id) { repository.deleteById(id); }
}

