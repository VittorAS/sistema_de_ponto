package com.system.electronic_point_app.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Funcionario {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long funcionarioId;

    private String nome;

    @Column(unique=true)
    private String cpf;

    private String email;

    private String cargo;

    private String telefone;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<RegistroPonto> registros;
}
