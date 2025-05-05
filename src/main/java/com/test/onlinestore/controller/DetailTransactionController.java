package com.test.onlinestore.controller;

import com.test.onlinestore.model.Buyer;
import com.test.onlinestore.model.DetailTransaction;
import com.test.onlinestore.service.DetailTransactionService;
import com.test.onlinestore.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail-transactions")
@RequiredArgsConstructor
public class DetailTransactionController {

    private final DetailTransactionService detailTransactionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DetailTransaction>>> getAll() {
        var detailTransaction = detailTransactionService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "Detail Transaction  fetched successfully", detailTransaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DetailTransaction>> getById(@PathVariable Long id) {
        var detailTransaction = detailTransactionService.findById(id);
        if (detailTransaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Detail Transaction  not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Detail Transaction  found", detailTransaction));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DetailTransaction>> create(@RequestBody DetailTransaction data) {
        var saved = detailTransactionService.save(data);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Detail Transaction  created", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DetailTransaction>> update(@PathVariable Long id, @RequestBody DetailTransaction data) {
        var detailTransaction = detailTransactionService.updateDetailTransaction(id, data);
        if (detailTransaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Detail Transaction  not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Detail Transaction updated", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        detailTransactionService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Detail Transaction deleted", null));
    }
}

