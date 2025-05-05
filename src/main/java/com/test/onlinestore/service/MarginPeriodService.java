package com.test.onlinestore.service;

import com.test.onlinestore.model.MarginPeriod;
import com.test.onlinestore.model.MarginPeriod;
import com.test.onlinestore.repository.MarginPeriodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarginPeriodService {
    private final MarginPeriodRepository repository;

    public List<MarginPeriod> findAll() { return repository.findAll(); }
    public MarginPeriod save(MarginPeriod dt) { return repository.save(dt); }
    public MarginPeriod findById(Long id) { return repository.findById(id).orElse(null); }
    public MarginPeriod updateMarginPeriod(Long id, MarginPeriod updatedMarginPeriod) {
        return repository.findById(id)
                .map(existingMarginPeriod -> {
                    existingMarginPeriod.setPeriodName(updatedMarginPeriod.getPeriodName());
                    existingMarginPeriod.setPercent(updatedMarginPeriod.getPercent());
                    return repository.save(existingMarginPeriod);
                })
                .orElse(null);
    }
    public void deleteById(Long id) { repository.deleteById(id); }
}

