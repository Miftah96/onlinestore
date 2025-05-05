package com.test.onlinestore.dto;

import com.test.onlinestore.model.DetailTransaction;
import com.test.onlinestore.model.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TransactionDTO {
    private Long buyerId;
    private Long marginId;
    private LocalDate trxDate;
    private List<ItemDTO> items;

    @Data
    public static class ItemDTO {
        private Long productId;
        private int quantity;

        // getters and setters
    }

}
