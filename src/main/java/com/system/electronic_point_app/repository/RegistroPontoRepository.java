package com.system.electronic_point_app.repository;

import com.system.electronic_point_app.model.RegistroPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {
//  Busca pelo ultimo registro de ponto do funcionário!
    @Query("SELECT r FROM RegistroPonto r WHERE r.funcionario.id = :funcionarioId ORDER BY r.dataHora DESC LIMIT 1")
    Optional<RegistroPonto> buscarUltimoRegistro(@Param("funcionarioId")  Long funcionarioId);

    List<RegistroPonto> findByFuncionarioIdOrderByDataHoraAsc(Long funcionarioId);
}
