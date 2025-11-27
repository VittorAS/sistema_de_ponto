package com.system.electronic_point_app.dto;

import java.util.List;

public record EspelhoPontoDTO(
        String nomeFuncionario,
        String cpf,
        String email,
        String cargo,
        String telefone,
        List<RegistroDTO> registros
) {
}
