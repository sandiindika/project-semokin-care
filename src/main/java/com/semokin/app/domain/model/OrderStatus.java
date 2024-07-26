package com.semokin.app.domain.model;

import com.semokin.app.adapter.config.constant.TableNames;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = TableNames.ORDER_STATUS)
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Name can't be empty")
    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;
}
