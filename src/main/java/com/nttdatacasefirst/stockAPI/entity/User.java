package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.Role;
import com.nttdatacasefirst.stockAPI.entity.enums.converters.RoleConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode*/
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @Column(length = 8, unique = true, name = "register_no")
    @GeneratedValue(generator =  "custom-id")
    @GenericGenerator(name = "custom-id", strategy ="com.nttdatacasefirst.stockAPI.config.CustomIdGenerator")
    private Long id;
    private String firstName;
    private String lastname;
    private String email;
    private String password;

    @Convert(converter = RoleConverter.class)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ShareHolder shareHolder;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getLabel()/*role.name()*/));
    }


    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public String getPassword() {
        return password;
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
}
