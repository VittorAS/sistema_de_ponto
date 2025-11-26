package com.system.electronic_point_app.model;

import com.system.electronic_point_app.enums.TipoRegistro;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class RegistroPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registroPontoId;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipo;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
}
