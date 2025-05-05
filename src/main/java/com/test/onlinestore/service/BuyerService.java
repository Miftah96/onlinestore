package com.test.onlinestore.service;

import com.test.onlinestore.model.Buyer;
import com.test.onlinestore.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerService {
    private final BuyerRepository repository;

    public List<Buyer> findAll() { return repository.findAll(); }
    public Buyer save(Buyer buyer) { return repository.save(buyer); }
    public Buyer findById(Long id) { return repository.findById(id).orElse(null); }
    public Buyer updateBuyer(Long id, Buyer updatedBuyer) {
        return repository.findById(id)
                .map(existingBuyer -> {
                    existingBuyer.setEmail(updatedBuyer.getEmail());
                    return repository.save(existingBuyer);
                })
                .orElse(null);
    }
    public void deleteById(Long id) { repository.deleteById(id); }
}

