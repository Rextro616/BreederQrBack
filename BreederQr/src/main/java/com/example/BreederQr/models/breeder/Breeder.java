package com.example.BreederQr.models.breeder;

import com.example.BreederQr.models.breedingplace.BreedingPlace;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Breeder implements UserDetails {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String last_name;

    @Column(nullable = false, length = 20)
    private String second_last_name;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 30)
    private String mail;

    @JsonIgnore
    @OneToOne(mappedBy = "breeder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BreedingPlace breedingPlace;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //Auditable
    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;
    @Column(updatable = false)
    int createdBy;
    @CreatedDate
    @Column
    LocalDateTime updatedAt;
    @Column
    int updatedBy;
    @Column()
    Boolean deleted;
    @Column(updatable = false)
    LocalDateTime deletedAt;
    @Column(updatable = false)
    int deletedBy;
}
