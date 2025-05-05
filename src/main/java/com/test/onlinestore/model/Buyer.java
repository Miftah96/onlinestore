package com.test.onlinestore.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "buyer")
@Data
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
}

