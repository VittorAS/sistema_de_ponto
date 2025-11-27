package com.system.electronic_point_app.dto;

import com.system.electronic_point_app.enums.TipoRegistro;

import java.time.LocalDateTime;

public record RegistroDTO (LocalDateTime dataHora, TipoRegistro tipoRegistro) {
}
