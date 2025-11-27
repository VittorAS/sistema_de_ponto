package com.system.electronic_point_app.repository;

import com.system.electronic_point_app.model.Funcionario;
import com.system.electronic_point_app.model.RegistroPonto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<RegistroPontoRepository> findByFuncionarioId(Long funcionarioId);

}
