package com.system.electronic_point_app.service;


import com.system.electronic_point_app.dto.EspelhoPontoDTO;
import com.system.electronic_point_app.dto.RegistroDTO;
import com.system.electronic_point_app.enums.TipoRegistro;
import com.system.electronic_point_app.model.Funcionario;
import com.system.electronic_point_app.model.RegistroPonto;
import com.system.electronic_point_app.repository.FuncionarioRepository;
import com.system.electronic_point_app.repository.RegistroPontoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroPontoService {
    private final RegistroPontoRepository registroPontoRepository;
    private final FuncionarioRepository funcionarioRepository;

//  Construtor
    public RegistroPontoService(RegistroPontoRepository registroPontoRepository,  FuncionarioRepository funcionarioRepository) {
        this.registroPontoRepository = registroPontoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }


//  Método de Registro de Ponto usando o Id do Funcionário e o Tipo do Registro
    public RegistroPonto registrar(Long funcionarioId, TipoRegistro tipo){

//  Busca de Funcionário no Banco, caso não encontre, lança a exceção
        Funcionario funcionario =  funcionarioRepository.findById(funcionarioId).orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o Id: " + funcionarioId));

//  Busca o ÚLTIMO registro do Fucionário
        Optional<RegistroPonto> ultimoRegistroOpt = registroPontoRepository.buscarUltimoRegistro(funcionarioId);

//  Valida as Regras de Negócio (O funcionário não pode Registrar o ponto de Saída sem Registrar a Entrada
        if(ultimoRegistroOpt.isPresent()){
            RegistroPonto ultimoRegistro = ultimoRegistroOpt.get();
            validarSequencia(ultimoRegistro.getTipo(), tipo);
        } else {
            if(tipo != TipoRegistro.ENTRADA){
                throw new IllegalArgumentException("O primeiro registro do funcionário deve ser uma ENTRADA.");
            }
        }

//  Objeto do Ponto, no caso, o horário do servidor, o tipo de ponto e o funcionário que está realizando a operação
        RegistroPonto registroPonto = new RegistroPonto();
        registroPonto.setDataHora(LocalDateTime.now());
        registroPonto.setTipo(tipo);
        registroPonto.setFuncionario(funcionario);

//  Salva no banco as informações do ponto
        return  registroPontoRepository.save(registroPonto);
    }

    public EspelhoPontoDTO gerarEspelho(Long funcionarioId){
//  Busca de Funcionário no Banco, caso não encontre, lança a exceção

        Funcionario funcionario =  funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o Id: " + funcionarioId));

        List<RegistroPonto> registro = registroPontoRepository.findByFuncionarioIdOrderByDataHoraAsc(funcionarioId);

        List<RegistroDTO> registrosDTO = registro.stream()
                .map(r ->new RegistroDTO(r.getDataHora(), r.getTipo()))
                .collect(Collectors.toList());

        return new EspelhoPontoDTO(funcionario.getNome(), funcionario.getCpf(), funcionario.getEmail(), funcionario.getCargo(), funcionario.getTelefone(), registrosDTO);
    }


//  Método para organizar as regras
    private void  validarSequencia(TipoRegistro ultimoTipo, TipoRegistro novoTipo) {

//  Não pode bater o mesmo ponto 2x seguidas
        if (ultimoTipo == novoTipo){
            throw new IllegalArgumentException("Não é permitido registrar " + novoTipo + " duas vezes seguidas.");
        }

//  Coerências
        if (ultimoTipo == TipoRegistro.ENTRADA && novoTipo == TipoRegistro.FIM_INTERVALO) {
            throw new IllegalArgumentException("Você acabou de entrar, não pode finalizar um intervalo que não começou.");
        }
        if (ultimoTipo == TipoRegistro.SAIDA && novoTipo != TipoRegistro.ENTRADA){
            throw new IllegalArgumentException("Você saiu. O próximo registro deve ser uma ENTRADA.");
        }
    }
}
