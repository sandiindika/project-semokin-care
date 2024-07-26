package com.semokin.app.domain.model;

import com.semokin.app.adapter.config.constant.TableNames;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = TableNames.STAFF)
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Name can't be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Phone number can't be empty")
    @Size(min = 10, max = 15)
    @Pattern(regexp = "\\+?[0-9]+", message = "Phone number is not valid")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotBlank(message = "Address can't be empty")
    @Column(nullable = false)
    private String address;

    @NonNull
    @Column(name = "date_of_birth", nullable = false)
    private Long dateOfBirth;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @NonNull
    @Column(nullable = false)
    private Long salary;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private StaffPosition staffPosition;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "profile_picture_id", referencedColumnName = "id")
    private ProfilePicture profilePicture;
}
