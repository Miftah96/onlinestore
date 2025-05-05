package com.test.onlinestore.service;

import com.test.onlinestore.dto.TransactionDTO;
import com.test.onlinestore.model.*;
import com.test.onlinestore.model.Transaction;
import com.test.onlinestore.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;

    private final TransactionRepository transactionRepository;
    private final DetailTransactionRepository detailTransactionRepository;
    private final ProductRepository productRepository;
    private final BuyerRepository buyerRepository;
    private final MarginPeriodRepository marginPeriodRepository;

    private static final Double SHIPPING_COST = 10000.0;
    private static final Double TAX_PERCENT = 10.0;

    public List<Transaction> findAll() { return repository.findAll(); }

    @Transactional
    public Transaction save(TransactionDTO request) {
        Buyer buyer = buyerRepository.findById(request.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        System.out.println("request "+buyer.getEmail());

        MarginPeriod margin = marginPeriodRepository.findById(request.getMarginId())
                .orElseThrow(() -> new RuntimeException("Margin period not found"));


        System.out.println("request "+buyer.getEmail());

        Transaction trx = new Transaction();
        trx.setBuyer(buyer);
        trx.setTrxDate(request.getTrxDate());
        trx.setShippingCost(SHIPPING_COST);
        trx.setTax(TAX_PERCENT);
        trx.setMarginPeriod(margin);

        // set total_payment = 0
        transactionRepository.save(trx);

        BigDecimal subtotal = BigDecimal.ZERO;

        for (TransactionDTO.ItemDTO item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            BigDecimal productSubtotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

            DetailTransaction detail = new DetailTransaction();
            detail.setTransaction(trx);
            detail.setProduct(product);
            detail.setQuantity(item.getQuantity());
            detail.setSubtotal(productSubtotal);

            subtotal = subtotal.add(productSubtotal);
            detailTransactionRepository.save(detail);
        }

        // Calc total payment
        BigDecimal marginAmount = subtotal.multiply(BigDecimal.valueOf(margin.getPercent()).divide(BigDecimal.valueOf(100)));
        BigDecimal taxAmount = subtotal.multiply(BigDecimal.valueOf(TAX_PERCENT / 100));

        BigDecimal totalPayment = subtotal.add(marginAmount).add(BigDecimal.valueOf(SHIPPING_COST)).add(taxAmount);
        trx.setTotalPayment(totalPayment);

        return transactionRepository.save(trx);
    }
    public Transaction findById(Long id) { return repository.findById(id).orElse(null); }
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        return repository.findById(id)
                .map(existingTransaction -> {
                    existingTransaction.setTax(updatedTransaction.getTax());
                    existingTransaction.setBuyer(updatedTransaction.getBuyer());
                    existingTransaction.setShippingCost(updatedTransaction.getShippingCost());
                    existingTransaction.setMarginPeriod(updatedTransaction.getMarginPeriod());
                    existingTransaction.setTrxDate(updatedTransaction.getTrxDate());
                    existingTransaction.setTotalPayment(updatedTransaction.getTotalPayment());
                    existingTransaction.setDetailTransactions(updatedTransaction.getDetailTransactions());
                    return repository.save(existingTransaction);
                })
                .orElse(null);
    }
    public void deleteById(Long id) { repository.deleteById(id); }
}

