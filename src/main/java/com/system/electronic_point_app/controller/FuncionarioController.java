package com.system.electronic_point_app.controller;

import com.system.electronic_point_app.model.Funcionario;
import com.system.electronic_point_app.service.FuncionarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

//  Construtor
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
//  Método POST do Controller
    @PostMapping
    public Funcionario cadastrar(@RequestBody Funcionario funcionario){
        return funcionarioService.cadastrarFuncionario(funcionario);
    }
}
