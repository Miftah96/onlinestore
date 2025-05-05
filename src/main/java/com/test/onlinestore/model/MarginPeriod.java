package com.test.onlinestore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "margin_period")
@Data
public class MarginPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "period_name")
    private String periodName;

    private Double percent;
}

