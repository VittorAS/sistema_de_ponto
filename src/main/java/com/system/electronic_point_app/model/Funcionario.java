package com.system.electronic_point_app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Funcionario {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique=true)
    private String cpf;

    private String email;

    private String cargo;

    private String telefone;

//  O relacionamento do funcionário
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
//  Apenas para manter o JSON limpo no teste no Postman
    @JsonIgnore
    private List<RegistroPonto> registros;
}
