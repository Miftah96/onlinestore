package com.test.onlinestore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trx_date")
    private LocalDate trxDate;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @Column(name = "shipping_cost")
    private Double shippingCost;

    private Double tax;

    @ManyToOne
    @JoinColumn(name = "margin_id")
    private MarginPeriod marginPeriod;

    @Column(name = "total_payment")
    private BigDecimal totalPayment;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<DetailTransaction> detailTransactions = new ArrayList<>();
}

