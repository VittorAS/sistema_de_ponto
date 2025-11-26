package com.looptech.electronic_point_app.entities;


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
    private String cpf;
    private String cargo;

    @OneToMany(mappedBy = "funcionario")
    private List<RegistroPonto> registros;
}
