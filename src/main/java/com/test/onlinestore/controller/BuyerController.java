package com.test.onlinestore.controller;

import com.test.onlinestore.model.Buyer;
import com.test.onlinestore.service.BuyerService;
import com.test.onlinestore.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyers")
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Buyer>>> getAll() {
        var buyer = buyerService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "Buyer fetched successfully", buyer));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Buyer>> getById(@PathVariable Long id) {
        var buyer = buyerService.findById(id);
        if (buyer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Buyer not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Buyer found", buyer));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Buyer>> create(@RequestBody Buyer buyer) {
        var saved = buyerService.save(buyer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Buyer created", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Buyer>> update(@PathVariable Long id, @RequestBody Buyer data) {
        var buyer = buyerService.updateBuyer(id, data);
        if (buyer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Buyer not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Buyer updated", buyer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        buyerService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Buyer deleted", null));
    }
}

