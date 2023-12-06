package com.setkies.sinp.domain.user;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.setkies.sinp.domain.wallet.Wallet;
import com.setkies.sinp.infrastructure.google.feign.dto.response.GoogleInfoResponse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private String profileImage;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id")
    @Builder.Default
    private Wallet wallet = new Wallet();

    public User update(GoogleInfoResponse response) {
        this.name = response.name();
        return this;
    }
}
