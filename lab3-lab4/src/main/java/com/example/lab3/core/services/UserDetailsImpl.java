package com.example.lab3.core.services;

import com.example.lab3.core.models.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private List<GrantedAuthority> authority;

    public static UserDetailsImpl build(Owner owner) {
        var role = new SimpleGrantedAuthority("ROLE_" + owner.getRole());
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(role);

        return new UserDetailsImpl(
                owner.getId(),
                owner.getUsername(),
                owner.getPassword(),
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
