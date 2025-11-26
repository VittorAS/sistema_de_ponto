package com.system.electronic_point_app.controller;

import com.system.electronic_point_app.enums.TipoRegistro;
import com.system.electronic_point_app.model.Funcionario;
import com.system.electronic_point_app.model.RegistroPonto;
import com.system.electronic_point_app.service.RegistroPontoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pontos")
public class RegistroPontoController {
    private final RegistroPontoService registroPontoService;

//  Construtor
    public RegistroPontoController(RegistroPontoService registroPontoService) {
        this.registroPontoService = registroPontoService;
    }

//  Método POST do Controller de Ponto
    @PostMapping
    public RegistroPonto cadastrar(@RequestParam Long funcionarioId, @RequestParam TipoRegistro tipoRegistro){
        return registroPontoService.registrar(funcionarioId, tipoRegistro);
    }
}
