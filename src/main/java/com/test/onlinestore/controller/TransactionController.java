package com.test.onlinestore.controller;

import com.test.onlinestore.dto.TransactionDTO;
import com.test.onlinestore.model.DetailTransaction;
import com.test.onlinestore.model.Product;
import com.test.onlinestore.model.Transaction;
import com.test.onlinestore.service.ProductService;
import com.test.onlinestore.service.TransactionService;
import com.test.onlinestore.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Transaction>>> getAll() {
        var transactions = transactionService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "  Transaction  fetched successfully", transactions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Transaction>> getById(@PathVariable Long id) {
        var transaction = transactionService.findById(id);
        if (transaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "  Transaction  not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "  Transaction  found", transaction));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransactionDTO buyer) {
        var saved = transactionService.save(buyer);
        System.out.println("LOG "+saved);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "  Transaction  created", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Transaction>> update(@PathVariable Long id, @RequestBody Transaction data) {
        var transaction = transactionService.updateTransaction(id, data);
        if (transaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "  Transaction  not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "  Transaction updated", transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "  Transaction deleted", null));
    }
}

