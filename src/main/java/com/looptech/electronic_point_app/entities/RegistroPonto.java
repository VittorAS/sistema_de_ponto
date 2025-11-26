package com.looptech.electronic_point_app.entities;

import com.looptech.electronic_point_app.enums.TipoRegistro;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RegistroPonto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipo;

    @ManyToOne
    private Funcionario funcionario;
}
