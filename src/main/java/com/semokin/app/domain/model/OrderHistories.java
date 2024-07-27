package com.semokin.app.domain.model;

import com.semokin.app.adapter.config.constant.TableNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = TableNames.ORDER_HISTORY)
public class OrderHistories {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
}
