package com.oncemore.store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "ReceiverName", nullable = false)
    private String receiverName;

    @Column(name = "Amount", nullable = false)
    private double amount;

    @Column(name = "IsPayment", nullable = false)
    private boolean isPayment;

    @Column(name = "PaymentMethod", nullable = false)
    private String paymentMethod;
}
