package com.example.periodmanagement.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomUserDetails implements UserDetails {
    @JsonProperty("username")
    String username;

    @JsonProperty("password")
    String password;

    @JsonProperty("fullName")
    String fullName;

    @JsonProperty("avatar")
    String avatar;

    @JsonProperty("superAdmin")
    boolean superAdmin;

    @JsonProperty("authorities")
    @JsonDeserialize(using = AuthoritiesDeserializer.class) // Deserialize authorities
    List<SimpleGrantedAuthority> authorities;

    @JsonProperty("enabled")
    boolean enabled;

    @JsonProperty("accountNonExpired")
    boolean accountNonExpired;

    @JsonProperty("credentialsNonExpired")
    boolean credentialsNonExpired;

    @JsonProperty("accountNonLocked")
    boolean accountNonLocked;


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
