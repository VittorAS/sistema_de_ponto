package com.system.electronic_point_app.service;


import com.system.electronic_point_app.model.Funcionario;
import com.system.electronic_point_app.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

//  Construtor
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

//  Método de casdastro de Funcionário
    public Funcionario cadastrarFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }
}
