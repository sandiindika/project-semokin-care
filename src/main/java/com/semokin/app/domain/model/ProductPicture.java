package com.semokin.app.domain.model;

import com.semokin.app.adapter.config.constant.TableNames;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = TableNames.PRODUCT_PICTURE)
public class ProductPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Filename can't be empty")
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private Long size;

    @NotBlank(message = "URL can't be empty")
    @Column(nullable = false)
    private String url;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}
