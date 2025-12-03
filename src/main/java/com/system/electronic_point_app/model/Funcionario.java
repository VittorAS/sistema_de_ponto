package com.system.electronic_point_app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Funcionario implements UserDetails {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique=true)
    private String cpf;
    private String email;
    private String cargo;
    private String telefone;

    private String senha;

//  O relacionamento do funcionário
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
//  Apenas para manter o JSON limpo no teste no Postman
    @JsonIgnore
    private List<RegistroPonto> registros;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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
